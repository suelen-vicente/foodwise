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
    var barCode: String?
    var price: Double
    var photo: Data?
    var packageQuantity: Double
    var quantityUnit: QuantityUnit
    
    public func hash(into hasher: inout Hasher) {
        return hasher.combine(id)
    }
    
    static func == (lhs: Ingredient, rhs: Ingredient) -> Bool {
        return lhs.id == rhs.id &&
               lhs.name == rhs.name
    }
}

enum QuantityUnit: String, Identifiable, CaseIterable {
    var id: String { self.rawValue }
    case ml = "ml"
    case g = "g"
    case l = "l"
    case kg = "kg"
    case teaSpoon = "tsp"
    case tableSpoon = "tbsp"
    case cup = "cup"
    case pinch = "pinch"
}
