//
//  IngredientService.swift
//  Foodrama
//
//  Created by Suelen Vicente on 2024-01-08.
//

import Foundation
import Moya

enum IngredientService {
    case createIngredient(ingredient: Ingredient)
    case getAllIngredients
    case getOneIngredient(id : Int)
    case getIngredientByBarCode(barCode: String)
    case updateIngredient(id:Int , ingredient: Ingredient)
    case deleteIngredient(id : Int)
}

extension IngredientService : TargetType {
    var baseURL: URL {
        return URL(string: "http://localhost:8080/ingredient")!
    }
    
    var path: String {
        switch self {
        case .getAllIngredients , .createIngredient(_), .getIngredientByBarCode(_):
            return "/"
        case  .deleteIngredient(let id) , .updateIngredient( let id,_), .getOneIngredient(let id) :
            return "/\(id)"
        default:
            break
        }
        
    }
    
    var method: Moya.Method {
        switch self {
        case .createIngredient(_):
          return  .post
        case .getAllIngredients, .getIngredientByBarCode(_), .getOneIngredient(_):
          return  .get
        case .updateIngredient(_ , _):
            return . put
        case .deleteIngredient(_):
            return .delete
        default:
            break
        }
    }
   
    var sampleData: Data {
        switch self {
        case .createIngredient(let ingredient):
            var barCodeTuple = ""
            
            if let barcode = ingredient.barCode {
                barCodeTuple = "\"barCode\": \"\(barcode)\""
            }
            return "{\"id\": \(ingredient.id), \(barCodeTuple), \"name\": \"\(ingredient.name)\", \"price\": \(ingredient.price), \"packageQuantity\": \(ingredient.packageQuantity), \"quantityUnit\": \"\(ingredient.quantityUnit.rawValue)\"}".data(using: .utf8)!
        case .getAllIngredients:
            return Data("{\"id\": 99999, \"barCode\": \"AHDBJ99887KKK88\", \"name\": \"Test\", \"price\": 99.00, \"packageQuantity\": 750, \"quantityUnit\": \"G\"}".data(using: .utf8)!)
        case .getIngredientByBarCode(let barCode):
            return Data("{\"id\": 99999, \"barCode\": \"\(barCode)\", \"name\": \"Test\", \"price\": 99.00, \"packageQuantity\": 750, \"quantityUnit\": \"G\"}".data(using: .utf8)!)
        case .getOneIngredient(let id):
            return Data("{\"id\": \(id), \"barCode\": \"AHDBJ99887KKK88\", \"name\": \"Test\", \"price\": 99.00, \"packageQuantity\": 750, \"quantityUnit\": \"G\"}".data(using: .utf8)!)
        case .updateIngredient(let id , let ingredient):
            var barCodeTuple = ""
            if let barcode = ingredient.barCode {
                barCodeTuple = "\"barCode\": \"\(barcode)\""
            }
            return "{\"id\": \(id), \(barCodeTuple), \"name\": \"\(ingredient.name)\", \"price\": \(ingredient.price), \"packageQuantity\": \(ingredient.packageQuantity), \"quantityUnit\": \"\(ingredient.quantityUnit.rawValue)\"}".data(using: .utf8)!
        case .deleteIngredient(let id):
            return "Deleted ingredient with id: \(id)".data(using: .utf8)!
        default:
            break
        }
    }
    
    var task: Task {
        switch self {
        case .getAllIngredients , .deleteIngredient(_):
            return .requestPlain
        case .createIngredient(let ingredient) , .updateIngredient(_ , let ingredient):
            return .requestParameters(parameters: ["name":name], encoding: JSONEncoding.default) 
        default:
            break
        }
    }
    
    var headers: [String : String]? {
        return ["Content_Typer" : "application/json"]
    }
    
    
}
