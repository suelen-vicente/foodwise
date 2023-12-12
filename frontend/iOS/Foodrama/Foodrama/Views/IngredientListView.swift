//
//  IngredientListView.swift
//  Foodrama
//
//  Created by Suelen Vicente on 2023-12-11.
//

import SwiftUI

struct IngredientListView: View {
    //This will be replaced by an API call
    // Returns only the ingredients available in my fridge
    // getMyFridgeIngredients
    @State var ingredients = IngredientList().restore()
    @State private var searchText = ""
    var addIngredientToRecipe: (_ ingredient: Ingredient) -> Void = {_ in }
    
    var body: some View {
        NavigationStack{
            //the alignment makes sure that the button is in the right position
            ZStack(alignment: .bottomTrailing){
                List{
                    ForEach(searchResults, id: \.self) { ingredient in
                        IngredientCellView(ingredient: ingredient, addToRecipe: addIngredientToRecipe)
                    }
                }
                FloatAddButton(action: {
                    addNewIngredient()
                }).padding(30)
            }.navigationTitle("Ingredients")
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
    var addToRecipe: (_ ingredient: Ingredient) -> Void = {_ in }
    
    @State private var wasAdded: Bool = false
    
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
            if !wasAdded {
                Button{
                    addToRecipe(ingredient)
                    wasAdded = true
                } label: {
                    Text("Add")
                        .font(.system(size: 14))
                        .foregroundStyle(.orange)
                }
            } else {
                Image(systemName: "checkmark")
                     .resizable()
                     .frame(width: 20, height: 15)
                     .foregroundStyle(.orange)
            }
        }
    }
}

#Preview {
    IngredientListView()
}
