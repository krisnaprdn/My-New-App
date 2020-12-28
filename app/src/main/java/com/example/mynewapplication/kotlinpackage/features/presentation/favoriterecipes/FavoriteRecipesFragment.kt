package com.example.mynewapplication.kotlinpackage.features.presentation.favoriterecipes

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynewapplication.R
import com.example.mynewapplication.kotlinpackage.core.exception.Failure
import com.example.mynewapplication.kotlinpackage.core.extension.*
import com.example.mynewapplication.kotlinpackage.core.platform.BaseFragment
import com.example.mynewapplication.kotlinpackage.features.presentation.model.RecipeView
import com.example.mynewapplication.kotlinpackage.features.presentation.recipes.RecipeItem
import com.google.android.material.snackbar.Snackbar
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_favorite_recipes.*
import timber.log.Timber

class FavoriteRecipesFragment : BaseFragment() {

    companion object {
        private val TAG = FavoriteRecipesFragment::class.java.simpleName
    }

    override fun layoutId() = R.layout.fragment_favorite_recipes

    private lateinit var viewModel: FavoriteRecipesViewModel

    private val recipesAdapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setHasOptionsMenu(true)

        viewModel = viewModel(viewModelFactory) {
            observe(favoriteRecipes, ::onRecipesFetched)
            failure(failure, ::showError)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_fav_recipes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recipesAdapter
        }

        viewModel.getFavoriteRecipes()
    }

    private fun onRecipesFetched(recipes: List<RecipeView>?) {
        progress_fav_recipes.gone()
        if (recipes != null && recipes.isNotEmpty()) {
            val items = recipes.map { recipeView ->
                RecipeItem(recipeView,
                    clickListenerRecipe = { recipe ->
                        if (recipe.href.isNotEmpty()) {
                            val navDirections =
                                FavoriteRecipesFragmentDirections.actionFavoriteRecipesFragmentToRecipeDetailsFragment()
                                    .apply {
                                        href = recipe.href
                                        name = recipe.title
                                    }
                            findNavController().navigate(navDirections)
                        } else {
                            Snackbar.make(fav_recipes_root, R.string.recipe_details_no_href, Snackbar.LENGTH_LONG)
                                .show()
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
            recipesAdapter.clear()
            recipesAdapter.addAll(items)
            rv_fav_recipes.visible()

        } else {
            notify(getString(R.string.recipes_no_fav_results))
        }
    }

    private fun showError(failure: Failure?) {
        progress_fav_recipes.gone()
        when (failure) {
            is Failure.DbGetFavoriteRecipesError -> {
                Timber.tag(TAG).d("DbGetFavoriteRecipesError: ${failure.exception.message}")
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                fragmentManager?.popBackStack()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}