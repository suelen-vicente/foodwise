//
//  UnitConversion.swift
//  Foodrama
//
//  Created by Suelen Vicente on 2023-11-28.
//

import Foundation

class UnitConversion {
    public static func convertToGrams(unit: QuantityUnit, quantity: Double) -> Double{
        switch unit {
            case .teaSpoon:
                return 5 * quantity
            case .tableSpoon:
                return 16 * quantity
            case .cup:
                return 250 * quantity
            case .pinch:
                return 0.3 * quantity
            case .l, .kg:
                return 1000 * quantity
            case .ml, .g:
                return 1 * quantity
        }
    }
}
