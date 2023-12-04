//
//  RecipeListView.swift
//  Foodrama
//
//  Created by Suelen Vicente on 2023-11-24.
//

import SwiftUI

struct RecipeListView: View {
    @State var recipes = RecipeList().restore()
    @State private var searchText = ""
    
    var body: some View {
        NavigationView{
            //the alignment makes sure that the button is in the right position
            ZStack(alignment: .bottomTrailing){
                List{
                    ForEach(searchResults, id: \.self) { recipe in
                        RecipeCellView(recipe: recipe)
                    }
                }
                FloatAddButton(action: {
                    addNewRecipe()
                }).padding(30)
            }.navigationTitle("Recipes")
             .navigationBarTitleDisplayMode(.inline)
        }.searchable(text: $searchText)
    }
    
    var searchResults: [Recipe] {
        if searchText.isEmpty {
            return recipes
        } else {
            return recipes.filter { $0.name.contains(searchText) }
        }
    }
    
    func addNewRecipe(){
        print("will push another view to the nav stack")
    }
}

struct RecipeCellView: View {
    @State var recipe: Recipe
    
    var body: some View {
        HStack{
            Image(systemName: "fork.knife.circle")
                 .resizable()
                 .frame(width: 50, height: 50)
                 .clipShape(Circle())
            HStack{
                VStack(alignment: .leading){
                    Text(recipe.name)
                        .font(.system(size: 14))
                        .padding(.bottom, 5)
                    Text("Portions: \(recipe.portionQuantity)")
                        .font(.system(size: 12))
                        .foregroundStyle(.gray)
                }
                Spacer()
                Text("$\(String(format: "%.2f", recipe.price))") //Format double to have 2 decimals
                    .font(.system(size: 12))
                    .foregroundStyle(.gray)
            }.padding(.vertical, 5)
            Spacer()
        }
    }
}

#Preview {
    RecipeListView()
}