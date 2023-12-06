//
//  Ingredient.swift
//  Foodrama
//
//  Created by Suelen Vicente on 2023-11-28.
//

import Foundation

struct Ingredient: Identifiable, Hashable {
    var id: Int
    var name: String
    var price: Double
    var photo: Data?
    var quantity: Double
    var quantityUnit: QuantityUnit
    //this could be another entity like "my fridge" or something
    var availableQuantity: Double = 0.0
    var availableQuantityUnit: QuantityUnit = .g
    
    public func hash(into hasher: inout Hasher) {
        return hasher.combine(id)
    }
    
    static func == (lhs: Ingredient, rhs: Ingredient) -> Bool {
        return lhs.id == rhs.id &&
               lhs.name == rhs.name
    }
}

enum QuantityUnit: String {
    case ml = "ml"
    case g = "g"
    case l = "l"
    case kg = "kg"
    case teaSpoon = "tsp"
    case tableSpoon = "tbsp"
    case cup = "cup"
    case pinch = "pinch"
}

struct IngredientList {
    func restore() -> [Ingredient]{
        var ingredientList: [Ingredient] = []
        
        var dicedTomatoCan = Ingredient(id: 1, name: "Diced Tomato Can", price: 1.77, quantity: 796, quantityUnit: .ml)
        var frozenVeggies = Ingredient(id: 2, name: "Frozen Mixed Veggies", price: 2.57, quantity: 750, quantityUnit: .g, availableQuantity: 200, availableQuantityUnit: .g)
        var groundBeef = Ingredient(id: 3, name: "Lean Ground Beef", price: 12.72, quantity: 1000, quantityUnit: .g)
        var basil = Ingredient(id: 4, name: "Basil", price: 2.27, quantity: 50, quantityUnit: .g)
        
        var butter = Ingredient(id: 4, name: "Butter", price: 1.43, quantity: 100, quantityUnit: .g, availableQuantity: 500, availableQuantityUnit: .g)
        var flour = Ingredient(id: 5, name: "Bread Flour", price: 1.59, quantity: 1000, quantityUnit: .g, availableQuantity: 1, availableQuantityUnit: .kg)
        var silk = Ingredient(id: 6, name: "Silk Protein", price: 2.14, quantity: 1000, quantityUnit: .ml, availableQuantity: 5, availableQuantityUnit: .l)
        var milk = Ingredient(id: 7, name: "Milk 2%", price: 1.5, quantity: 1000, quantityUnit: .ml, availableQuantity: 4, availableQuantityUnit: .l)
        var ketchup = Ingredient(id: 8, name: "Ketchup", price: 0.46, quantity: 100, quantityUnit: .ml, availableQuantity: 200, availableQuantityUnit: .g)
        var mustard = Ingredient(id: 9, name: "Dijon Mustard", price: 0.61, quantity: 100, quantityUnit: .ml)
        var shoyu = Ingredient(id: 10, name: "Kikkoman Shoyu", price: 0.83, quantity: 100, quantityUnit: .ml)
        var mushroom = Ingredient(id: 11, name: "Mushroom", price: 1.30, quantity: 100, quantityUnit: .g)
        var chickenBreast = Ingredient(id: 12, name: "Chicken Breast", price: 1.51, quantity: 100, quantityUnit: .g)
        
        var maple = Ingredient(id: 13, name: "Maple Syrup", price: 1.60, quantity: 100, quantityUnit: .ml, availableQuantity: 900, availableQuantityUnit: .ml)
        var wholeWheatFlour = Ingredient(id: 14, name: "Whole Wheat Flour", price: 0.15, quantity: 100, quantityUnit: .g, availableQuantity: 1, availableQuantityUnit: .kg)
        var instantYeast = Ingredient(id: 15, name: "Instant Yeast", price: 4.40, quantity: 100, quantityUnit: .g, availableQuantity: 50, availableQuantityUnit: .g)
        
        ingredientList.append(dicedTomatoCan)
        ingredientList.append(frozenVeggies)
        ingredientList.append(groundBeef)
        ingredientList.append(basil)
        ingredientList.append(butter)
        ingredientList.append(flour)
        ingredientList.append(silk)
        ingredientList.append(milk)
        ingredientList.append(ketchup)
        ingredientList.append(mustard)
        ingredientList.append(shoyu)
        ingredientList.append(mushroom)
        ingredientList.append(chickenBreast)
        ingredientList.append(maple)
        ingredientList.append(wholeWheatFlour)
        ingredientList.append(instantYeast)
        
        return ingredientList
    }
}
