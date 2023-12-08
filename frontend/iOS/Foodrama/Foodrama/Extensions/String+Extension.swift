//
//  String+Extension.swift
//  Foodrama
//
//  Created by Suelen Vicente on 2023-12-07.
//

import Foundation

extension String {
    static func formattedPrice(_ price: Double) -> String {
        return String(format: "$%.2f", price)
    }
    
    static func formattedQuantity(_ quantity: Double) -> String {
        return String(format: "$%.1f", quantity)
    }
}
