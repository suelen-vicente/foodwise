//
//  RecipeView.swift
//  Foodrama
//
//  Created by Suelen Vicente on 2023-12-04.
//

import SwiftUI

struct RecipeView: View {
    @ObservedObject var recipeVC: RecipeViewController = RecipeViewController()
    
    var body: some View {
        NavigationStack {
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
                                EditRecipeView(recipe: recipeVC.recipe)
                            } label: {
                                Text("Edit")
                                    .font(.body)
                                    .foregroundStyle(.orange)
                                    .multilineTextAlignment(.center)
                            }
                            if recipeVC.hasIngredientsInMyFridge {
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
                                    action: recipeVC.addMissingIngredientsToShoppingList,
                                    iconName: "cart",
                                    text: "Shop Missing Ingredients"
                                ).padding(.top, 20)
                            }
                            Spacer()
                            Text("Price: \(recipeVC.formattedPrice)")
                                .font(.system(size: 14))
                                .foregroundStyle(.black)
                            Spacer()
                            Text("Portions: \(recipeVC.recipe.portionQuantity)")
                                .font(.system(size: 14))
                                .foregroundStyle(.black)
                                .padding(.bottom, 20)
                            Spacer()
                        }.padding()
                    }
                    Text(recipeVC.recipe.name)
                        .font(.system(size: 24))
                        .foregroundStyle(.orange)
                        .multilineTextAlignment(.center)
                        .padding()
                    ColapsableTitle(show: $recipeVC.showDescription, title: "Description")
                        .padding(.horizontal)
                        .padding(.bottom, 5)
                    if recipeVC.showDescription {
                        Text("Essa é uma receita fácil para dias de semana preguiçosos com a vantagem de render para vários dias.")
                            .font(.system(size: 14))
                            .foregroundStyle(.gray)
                            .multilineTextAlignment(.leading)
                            .padding(.horizontal)
                            .padding(.bottom, 20)
                    }
                    ColapsableTitle(show: $recipeVC.showIngredients, title: "Ingredients")
                        .padding(.horizontal)
                        .padding(.bottom, 5)
                    if recipeVC.showIngredients {
                        RecipeIngredientsList(ingredients: recipeVC.recipe.listOfIngredients, mode: .readOnly, addIngredient: $recipeVC.addIngredients)
                         .padding(.bottom, 20)
                    }
                    ColapsableTitle(show: $recipeVC.showPreparation, title: "Preparation")
                        .padding(.horizontal)
                        .padding(.bottom, 5)
                    if recipeVC.showPreparation {
                        Text(recipeVC.recipe.steps)
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
}

#Preview {
    RecipeView()
}
