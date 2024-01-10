//
//  RecipeController.swift
//  Foodrama
//
//  Created by Suelen Vicente on 2023-12-12.
//

import Foundation
import SwiftUI

class RecipeViewController: ObservableObject {
    @Published var recipe: Recipe
    @Published var showDescription: Bool = true
    @Published var showIngredients: Bool = true
    @Published var showPreparation: Bool = true
    @Published var addIngredients: Bool = false
    
    @Published var showToast: Bool = false
    @Published var toastMessage: String = ""
    
    init(recipe: Recipe){
        self.recipe = recipe
    }
    
    convenience init(){
        self.init(recipe: RecipeList().restore().first!)
    }
    
    var hasIngredientsInMyFridge: Bool {
        //This will also be returned in an API
        // it has to check in the My Fridge table
        // if the ingredents and quantity required exists
        return false
    }
    
    var formattedPrice: String {
        return String.formattedPrice(recipe.price)
    }
    
    func addMissingIngredientsToShoppingList(){
        //Show toast if API call was succeeded
        showToast = true
        toastMessage = "Added all missing ingredients to shopping list."
    }
}
