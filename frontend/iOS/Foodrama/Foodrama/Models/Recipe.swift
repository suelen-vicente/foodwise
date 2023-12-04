//
//  Recipe.swift
//  Foodrama
//
//  Created by Suelen Vicente on 2023-11-24.
//

import Foundation

struct Recipe: Identifiable, Hashable {
    let id: Int
    var name: String
    var price: Double
    var photo: Data?
    var steps: String
    var portionQuantity: Int
    var listOfIngredients: [IngredientWithQuantity]
    
    public func hash(into hasher: inout Hasher) {
        return hasher.combine(id)
    }
    
    static func == (lhs: Recipe, rhs: Recipe) -> Bool {
        return lhs.id == rhs.id &&
               lhs.name == rhs.name
    }
}

struct IngredientWithQuantity {
    var ingredient: Ingredient
    var quantity: Double
    var quantityUnit: QuantityUnit
}

struct RecipeList {
    
    func restore() -> [Recipe]{
        var recipeList: [Recipe] = []
        let dicedTomatoCan = Ingredient(id: 1, name: "Diced Tomato Can", price: 1.77, quantity: 796, quantityUnit: .ml)
        let frozenVeggies = Ingredient(id: 2, name: "Frozen Mixed Veggies", price: 2.57, quantity: 750, quantityUnit: .g)
        let groundBeef = Ingredient(id: 3, name: "Lean Ground Beef", price: 12.72, quantity: 1000, quantityUnit: .g)
        let basil = Ingredient(id: 4, name: "Basil", price: 2.27, quantity: 50, quantityUnit: .g)
        
        let butter = Ingredient(id: 4, name: "Butter", price: 1.43, quantity: 100, quantityUnit: .g)
        let flour = Ingredient(id: 5, name: "Bread Flour", price: 1.59, quantity: 1000, quantityUnit: .g)
        let silk = Ingredient(id: 6, name: "Silk Protein", price: 2.14, quantity: 1000, quantityUnit: .ml)
        let milk = Ingredient(id: 7, name: "Milk 2%", price: 1.5, quantity: 1000, quantityUnit: .ml)
        let ketchup = Ingredient(id: 8, name: "Ketchup", price: 0.46, quantity: 100, quantityUnit: .ml)
        let mustard = Ingredient(id: 9, name: "Dijon Mustard", price: 0.61, quantity: 100, quantityUnit: .ml)
        let shoyu = Ingredient(id: 10, name: "Kikkoman Shoyu", price: 0.83, quantity: 100, quantityUnit: .ml)
        let mushroom = Ingredient(id: 11, name: "Mushroom", price: 1.30, quantity: 100, quantityUnit: .g)
        let chickenBreast = Ingredient(id: 12, name: "Chicken Breast", price: 1.51, quantity: 100, quantityUnit: .g)
        
        let maple = Ingredient(id: 13, name: "Maple Syrup", price: 1.60, quantity: 100, quantityUnit: .ml)
        let wholeWheatFlour = Ingredient(id: 14, name: "Whole Wheat Flour", price: 0.15, quantity: 100, quantityUnit: .g)
        let instantYeast = Ingredient(id: 15, name: "Instant Yeast", price: 4.40, quantity: 100, quantityUnit: .g)
        
        let onion = Ingredient(id: 16, name: "Onion", price: 0.22, quantity: 100, quantityUnit: .g)
        let garlicMinced = Ingredient(id: 17, name: "Minced Garlic", price: 0.69, quantity: 100, quantityUnit: .g)
        let oliveOil = Ingredient(id: 18, name: "Olive Oil", price: 1.29, quantity: 100, quantityUnit: .ml)
        
        let groundBeefWithTomatoSauce = Recipe(
            id: 1,
            name: "Ground Beef with Tomato Sauce", 
            price: 10.0,
            steps: "1. Refogar o alho e a cebola no azeite \n 2. Adicionar a carne e ir amassando \n 3. Quando a carne stiver bem frita, adicione o molho, os vegetais, o sal e o manjericao \n 4. Ferver ate ficar bom :)",
            portionQuantity: 6,
            listOfIngredients: [IngredientWithQuantity(ingredient: dicedTomatoCan, quantity: 1592, quantityUnit: .ml),
                                IngredientWithQuantity(ingredient: frozenVeggies, quantity: 250, quantityUnit: .g),
                                IngredientWithQuantity(ingredient: groundBeef, quantity: 500, quantityUnit: .g),
                                IngredientWithQuantity(ingredient: basil, quantity: 3, quantityUnit: .g),
                                IngredientWithQuantity(ingredient: onion, quantity: 100, quantityUnit: .g),
                                IngredientWithQuantity(ingredient: garlicMinced, quantity: 1, quantityUnit: .tableSpoon),
                                IngredientWithQuantity(ingredient: oliveOil, quantity: 1, quantityUnit: .tableSpoon)])
        
        let chickenStrogonoff = Recipe(
            id: 2,
            name: "Chicken Strogonoff", 
            price: 7.20,
            steps: "1. Refogar o alho e a cebola no azeite \n 2. Adicionar o frango picado e refogar ate ficar dourado \n 3. Remover o frango \n 4. Adicionar a manteiga e a farinha \n 5. Adicionar o leite e mexer ate engrossar \n 6. Adicionar o frango e o cogumelo \n 7. Adicionar o resto dos molhos",
            portionQuantity: 4,
            listOfIngredients: [
                IngredientWithQuantity(ingredient: butter, quantity: 50, quantityUnit: .g),
                IngredientWithQuantity(ingredient: onion, quantity: 100, quantityUnit: .g),
                IngredientWithQuantity(ingredient: chickenBreast, quantity: 1000, quantityUnit: .g),
                IngredientWithQuantity(ingredient: milk, quantity: 500, quantityUnit: .ml),
                IngredientWithQuantity(ingredient: ketchup, quantity: 250, quantityUnit: .g),
                IngredientWithQuantity(ingredient: mustard, quantity: 150, quantityUnit: .g),
                IngredientWithQuantity(ingredient: shoyu, quantity: 100, quantityUnit: .ml),
                IngredientWithQuantity(ingredient: flour, quantity: 2, quantityUnit: .tableSpoon)])
        
        let wholeWheatBread = Recipe(
            id: 3,
            name: "Whole Wheat Bread", 
            price: 3.00,
            steps: "1. Heat the milk and the butter 1 min in the microwave \n 2. Add the maple to the pan \n 3. Add te warm milk an butter \n 4. Add the flours and the yeast \n 5. Put on the breadmaker using 1.5lb config, light crust and on cicle 1",
            portionQuantity: 15,
            listOfIngredients: [
                IngredientWithQuantity(ingredient: maple, quantity: 4, quantityUnit: .tableSpoon),
                IngredientWithQuantity(ingredient: milk, quantity: 1, quantityUnit: .cup),
                IngredientWithQuantity(ingredient: butter, quantity: 4, quantityUnit: .tableSpoon),
                IngredientWithQuantity(ingredient: flour, quantity: 1, quantityUnit: .cup),
                IngredientWithQuantity(ingredient: wholeWheatFlour, quantity: 2, quantityUnit: .cup),
                IngredientWithQuantity(ingredient: instantYeast, quantity: 1, quantityUnit: .teaSpoon)])
        
        recipeList.append(groundBeefWithTomatoSauce)
        recipeList.append(chickenStrogonoff)
        recipeList.append(wholeWheatBread)
        
        return recipeList
    }
}
