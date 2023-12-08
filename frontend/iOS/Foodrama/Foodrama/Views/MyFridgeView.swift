//
//  MyFridgeView.swift
//  Foodrama
//
//  Created by Suelen Vicente on 2023-12-04.
//

import SwiftUI

struct MyFridgeView: View {
    //This will be replaced by an API call
    // Returns only the ingredients available in my fridge
    // getMyFridgeIngredients
    @State var ingredients = IngredientList().restore().filter { $0.availableQuantity > 0}
    @State private var searchText = ""
    
    var body: some View {
        NavigationView{
            //the alignment makes sure that the button is in the right position
            ZStack(alignment: .bottomTrailing){
                List{
                    ForEach(searchResults, id: \.self) { ingredient in
                        IngredientCellView(ingredient: ingredient)
                    }
                }
                FloatAddButton(action: {
                    addNewIngredient()
                }).padding(30)
            }.navigationTitle("My Fridge")
             .navigationBarTitleDisplayMode(.inline)
        }.searchable(text: $searchText)
    }
    
    var searchResults: [Ingredient] {
        if searchText.isEmpty {
            return ingredients
        } else {
            return ingredients.filter { $0.name.contains(searchText) }
        }
    }
    
    func addNewIngredient(){
        print("will push another view to the nav stack")
    }
}

struct IngredientCellView: View {
    @State var ingredient: Ingredient
    
    var body: some View {
        HStack{
            Image(systemName: "carrot")
                 .resizable()
                 .frame(width: 30, height: 30)
//                 .clipShape(Circle())
            Text(ingredient.name)
                .font(.system(size: 14))
                .padding(.bottom, 5).padding(.vertical, 5)
            Spacer()
            if ingredient.availableQuantity > 0 {
                Text("\(String.formattedQuantity(ingredient.availableQuantity)) \(ingredient.availableQuantityUnit.rawValue)")
                    .font(.system(size: 12))
                    .foregroundStyle(.gray)
            }
        }
    }
}

#Preview {
    MyFridgeView()
}
