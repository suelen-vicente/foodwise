//
//  RecipeView.swift
//  Foodrama
//
//  Created by Suelen Vicente on 2023-12-04.
//

import SwiftUI

struct RecipeView: View {
    @State var recipe: Recipe = RecipeList().restore().first!
    @State var showDescription: Bool = true
    @State var showIngredients: Bool = true
    @State var showPreparation: Bool = true
    
    //This will also be returned in an API
    // it has to check in the My Fridge table
    // if the ingredents and quantity required exists
    @State var hasIngredients: Bool = false
    
    var body: some View {
        NavigationView{
            ScrollView{
                VStack(alignment: .leading){
                    HStack{
                        Image(systemName: "fork.knife.circle")
                            .resizable()
                            .frame(width: 150, height: 150)
                            .clipShape(Circle())
                            .padding()
                        VStack(alignment: .trailing){
                            NavigationLink {
                                EditRecipeView(recipe: recipe)
                            } label: {
                                Text("Edit")
                                    .font(.body)
                                    .foregroundStyle(.orange)
                                    .multilineTextAlignment(.center)
                            }
                            if hasIngredients {
                                HStack{
                                    Image(systemName: "party.popper")
                                        .resizable()
                                        .frame(width: 30, height: 30)
                                        .foregroundStyle(.orange)
                                    Text("You have all the ingredients in your fridge!")
                                        .font(.system(size: 14))
                                        .foregroundStyle(.black)
                                        .multilineTextAlignment(.trailing)
                                }.padding(.bottom, 20)
                            } else {
                                FilledRectangularButton(
                                    action: addMissingIngredientsToShoppingList,
                                    iconName: "cart",
                                    text: "Shop Missing Ingredients"
                                ).padding(.top, 20)
                            }
                            Spacer()
                            Text("Price: \("$\(String.formattedPrice(recipe.price))")")
                                .font(.system(size: 14))
                                .foregroundStyle(.black)
                            Spacer()
                            Text("Portions: \(recipe.portionQuantity)")
                                .font(.system(size: 14))
                                .foregroundStyle(.black)
                                .padding(.bottom, 20)
                            Spacer()
                        }.padding()
                    }
                    Text(recipe.name)
                        .font(.system(size: 24))
                        .foregroundStyle(.orange)
                        .multilineTextAlignment(.center)
                        .padding()
                    ColapsableTitle(show: $showDescription, title: "Description")
                        .padding(.horizontal)
                        .padding(.bottom, 5)
                    if showDescription {
                        Text("Essa é uma receita fácil para dias de semana preguiçosos com a vantagem de render para vários dias.")
                            .font(.system(size: 14))
                            .foregroundStyle(.gray)
                            .multilineTextAlignment(.leading)
                            .padding(.horizontal)
                            .padding(.bottom, 20)
                    }
                    ColapsableTitle(show: $showIngredients, title: "Ingredients")
                        .padding(.horizontal)
                        .padding(.bottom, 5)
                    if showIngredients {
                        RecipeIngredientsList(ingredients: recipe.listOfIngredients, mode: .readOnly)
                         .padding(.bottom, 20)
                    }
                    ColapsableTitle(show: $showPreparation, title: "Preparation")
                        .padding(.horizontal)
                        .padding(.bottom, 5)
                    if showPreparation {
                        Text(recipe.steps)
                            .font(.system(size: 14))
                            .foregroundStyle(.gray)
                            .multilineTextAlignment(.leading)
                            .padding(.horizontal)
                            .padding(.bottom, 20)
                    }
                    Spacer()
                }
            }
        }
    }
    
    func addMissingIngredientsToShoppingList(){
        print("Call the API to add the missing ingredients to the shopping list")
    }
}

#Preview {
    RecipeView()
}
