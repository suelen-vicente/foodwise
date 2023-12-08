//
//  EditRecipeView.swift
//  Foodrama
//
//  Created by Suelen Vicente on 2023-12-06.
//

import SwiftUI

struct EditRecipeView: View {
    @State var recipe: Recipe? = nil
    
    @State var portions: String = ""
    @State var name: String = ""
    @State var description: String = ""
    @State var steps: String = ""
    @State var ingredients: [IngredientWithQuantity] = []
    
    @State var showDescription: Bool = true
    @State var showIngredients: Bool = true
    @State var showPreparation: Bool = true
    
    var body: some View {
        NavigationView{
            VStack{
                ScrollView{
                    VStack(alignment: .leading, spacing: 8){
                        HStack(alignment: .center){
                            Image(systemName: "fork.knife.circle")
                                .resizable()
                                .frame(width: 150, height: 150)
                                .clipShape(Circle())
                                .padding()
                            Spacer()
                            VStack(alignment: .trailing){
                                Spacer()
                                Text("Price: \(String.formattedPrice(recipe?.price ?? 0.0))")
                                    .font(.system(size: 14))
                                    .foregroundStyle(.gray)
                                Spacer()
                                TextFieldWithHint(
                                    text: $portions,
                                    validation: validatePortion,
                                    label: "Number of Portions",
                                    hint: "Portion must be a number between 0 and 100",
                                    keyboardType: .numberPad)
                                Spacer()
                            }.padding(.horizontal)
                        }
                        TextFieldWithHint(
                            text: $name,
                            validation: validateName,
                            hint: "Name must have between 5 and 100 characters",
                            placeholder: "Name")
                        .padding(.horizontal)
                        .padding(.bottom, 5)
                        ColapsableTitle(show: $showDescription, title: "Description")
                            .padding(.horizontal)
                        if showDescription {
                            TextFieldWithHint(
                                text: $description,
                                validation: validateDescription,
                                hint: "Description must have between 5 and 500 characters",
                                placeholder: "Description",
                                lineLimit: 5)
                            .padding(.horizontal)
                            .padding(.bottom, 5)
                        }
                        ColapsableTitle(show: $showIngredients, title: "Ingredients")
                            .padding(.horizontal)
                            .padding(.bottom, 5)
                        if showIngredients {
                            RecipeIngredientsList(ingredients: recipe?.listOfIngredients ?? [], mode: .edit)
                                .padding(.bottom, 20)
                        }
                        ColapsableTitle(show: $showPreparation, title: "Preparation")
                            .padding(.horizontal)
                            .padding(.bottom, 5)
                        if showPreparation {
                            TextFieldWithHint(
                                text: $description,
                                validation: validateDescription,
                                hint: "Description must have between 5 and 500 characters",
                                placeholder: "Description",
                                lineLimit: 5)
                            .padding(.horizontal)
                        }
                        Spacer()
                    }
                }
                SaveOrCancelBar(cancel: {
                    
                }, save: {
                    
                })
            }
        }
    }
    
    func validateName(name: String) -> Bool{
        return name.count >= 5 && name.count <= 100
    }
    
    func validatePortion(portion: String) -> Bool{
        guard let p = Int(portion) else { return false }
        return p > 0 && p <= 100
    }
    
    func validateDescription(description: String) -> Bool{
        return description.count >= 5 && description.count <= 500
    }
}

#Preview {
    EditRecipeView()
}
