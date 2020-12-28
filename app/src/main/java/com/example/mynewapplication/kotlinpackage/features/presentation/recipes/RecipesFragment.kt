package com.example.mynewapplication.kotlinpackage.features.presentation.recipes

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynewapplication.R
import com.example.mynewapplication.kotlinpackage.core.exception.Failure
import com.example.mynewapplication.kotlinpackage.core.extension.*
import com.example.mynewapplication.kotlinpackage.core.platform.BaseFragment
import com.example.mynewapplication.kotlinpackage.features.presentation.model.RecipeView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_content_list.*
import kotlinx.android.synthetic.main.fragment_recipes.*
import timber.log.Timber

class RecipesFragment : BaseFragment() {

    companion object {
        private val TAG = RecipesFragment::class.java.simpleName
        private const val ITEMS_BEFORE_FETCHING_NEXT_PAGE = 3
        private const val DELAY_AFTER_TYPING = 700L
        private const val DELAY_INTERVAL = 100L
    }

    override fun layoutId() = R.layout.activity_content_list

    private lateinit var viewModel: RecipesViewModel

    private lateinit var searchView: SearchView

    private lateinit var scrollListener: RecyclerView.OnScrollListener

    private val recipesAdapter = GroupAdapter<ViewHolder>()

    private var countDownTimer: CountDownTimer? = null

    private var loadingNewItems = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setHasOptionsMenu(true)

        viewModel = viewModel(viewModelFactory) {
            observe(recipeList, ::onRecipesFetched)
            observe(favoriteRecipes, ::onFavRecipesFetched)
            observe(restartSearch, ::onRestartSearch)
            failure(failure, ::showError)
        }
    }

    private fun onRestartSearch(restart: Boolean?) {
        restart?.let {
            if (restart) {
                loadingNewItems = false
                recipesAdapter.clear()
                removeScrollListener()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_contentlist.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recipesAdapter
        }
        addScrollListener()

        fetchRecipes("")
        viewModel.getFavoriteRecipes()
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//        inflater.inflate(R.menu.menu_main, menu)
//
//        val searchItem = menu.findItem(R.id.action_search)
//        searchView = searchItem.actionView as SearchView
//        searchView.apply {
//            setOnQueryTextListener(this@RecipesFragment)
//        }
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.action_favorites -> {
//                findNavController().navigate(RecipesFragmentDirections.actionRecipesFragmentToFavoriteRecipesFragment())
//                return true
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }

//    override fun onQueryTextSubmit(query: String?): Boolean {
//        query?.let {
//            fetchRecipes(it)
//        }
//        return true
//    }

//    override fun onQueryTextChange(newText: String?): Boolean {
//        countDownTimer?.cancel()
//        countDownTimer = object : CountDownTimer(DELAY_AFTER_TYPING, DELAY_INTERVAL) {
//            override fun onFinish() {
//                newText?.let {
//                    if (it.length > 2) fetchRecipes(it)
//                }
//            }
//
//            override fun onTick(millisUntilFinished: Long) {
//                Timber.tag(TAG).d("Remaining: $millisUntilFinished")
//            }
//        }.start()
//        return true
//    }

    private fun fetchRecipes(ingredients: String) {
        viewModel.getRecipes(ingredients.trim())
    }

    private fun onRecipesFetched(recipes: List<RecipeView>?) {
        progress_recipes.gone()
        if (recipes != null && recipes.isNotEmpty()) {
            val items = recipes.map { recipeView ->
                RecipeItem(recipeView,
                    clickListenerRecipe = { recipe ->
                        if (recipe.href.isNotEmpty()) {
                            val navDirections =
                                RecipesFragmentDirections.actionRecipesFragmentToRecipeDetailsFragment().apply {
                                    href = recipe.href
                                    name = recipe.title
                                }
                            findNavController().navigate(navDirections)
                        } else {
                            notify(getString(R.string.recipe_details_no_href))
                        }
                    },
                    clickListenerFav = { recipe, isFavorite ->
                        if (isFavorite) {
                            viewModel.saveRecipe(recipe)
                        } else {
                            viewModel.deleteRecipe(recipe.href)
                        }
                    })
            }
            if (loadingNewItems) {
                loadingNewItems = false
                recipesAdapter.removeGroup(recipesAdapter.itemCount - 1)
            }
            recipesAdapter.addAll(items)
            rv_contentlist.visible()

        } else if (recipesAdapter.itemCount > 0) {
            notify(getString(R.string.recipes_no_more_recipes))
        } else {
            notify(getString(R.string.recipes_no_results))
        }
    }

    private fun onFavRecipesFetched(favRecipes: List<RecipeView>?) {
        favRecipes?.let {
            if (it.isNotEmpty()) {
                for (i in 0 until recipesAdapter.itemCount) {
                    val recipeItem = recipesAdapter.getItem(i) as RecipeItem
                    recipeItem.recipeView.isFavorite =
                        favRecipes.find { favRecipe -> recipeItem.recipeView.href == favRecipe.href } != null
                }
            }
        }
    }

    private fun addScrollListener() {
        scrollListener = object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val isTimeToLoadMoreItems =
                    ITEMS_BEFORE_FETCHING_NEXT_PAGE >= recipesAdapter.itemCount - lastVisibleItemPosition
                if (!loadingNewItems && isTimeToLoadMoreItems) {
                    loadingNewItems = true
                    recipesAdapter.add(recipesAdapter.itemCount, LoadingItem())
                    viewModel.getRecipesNextPage()
                }
            }
        }
        rv_contentlist.addOnScrollListener(scrollListener)
    }

    private fun removeScrollListener() {
        rv_contentlist.removeOnScrollListener(scrollListener)
        Timber.tag(TAG).d("removeScrollListener: ${Exception().printStackTrace()}")
    }

    private fun showError(failure: Failure?) {
        when (failure) {
            is Failure.ServerError -> {
                Timber.tag(TAG).d("Server error: ${failure.throwable?.message}")
                if (loadingNewItems) {
                    loadingNewItems = false
                    recipesAdapter.removeGroup(recipesAdapter.itemCount - 1)
                    removeScrollListener()
                }
            }
        }
    }
}