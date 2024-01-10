//
//  RecipeIngredientsList.swift
//  Foodrama
//
//  Created by Suelen Vicente on 2023-12-07.
//

import SwiftUI

struct RecipeIngredientsList: View {
    @State var ingredients: [IngredientWithQuantity] = RecipeList().restore().first!.listOfIngredients
    var mode: RecipeIngredientListMode = .edit
    @Binding var addIngredient: Bool
    
    private let readOnlyHeight: Int = 20
    private let editHeight: Int = 40
    
    var body: some View {
        VStack() {
            if mode == .edit {
                addButton
            }
            if ingredients.isEmpty {
                Text("You didn't add any ingredients here yet. Let's do this!")
                    .font(.system(size: 14))
                    .foregroundColor(.gray)
                    .multilineTextAlignment(.center)
                    .padding(.horizontal)
            } else {
                List{
                    ForEach(ingredients, id: \.self) { i in
                        if mode == .readOnly {
                            RecipeIngredientCellReadonly(recipeIngredient: i)
                        } else {
                            RecipeIngredientCellEdit(recipeIngredient: i)
                        }
                    }.onDelete(perform: mode == .readOnly ? {_ in } : deleteIngredient)
                }.listStyle(PlainListStyle())
                // Edit mode needs more space in each cell, so the list should be bigger as well
                    .frame(height: CGFloat((mode == .readOnly ? readOnlyHeight : editHeight)) * CGFloat(ingredients.count))
            }
        }
    }
    
    var addButton: some View {
        Button(action: {
            addIngredient.toggle()
        }) {
            HStack() {
                Spacer()
                Image(systemName: "plus")
                    .font(.body)
                    .foregroundColor(.orange)
            }
            .padding(.vertical, 8)
            .padding(.horizontal)
        }
    }
    
    func deleteIngredient(at offsets: IndexSet){
        //call api to remove recipe ingredient and refresh the list
    }
}

struct RecipeIngredientCellEdit: View {
    @State var recipeIngredient: IngredientWithQuantity
    @State var quantity: String = "0"
    @State var quantityUnit: QuantityUnit = .g
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack{
                Text(recipeIngredient.ingredient.name)
                    .font(.system(size: 14))
                    .foregroundStyle(.gray)
                    .frame(width: 100, alignment: .leading)
                Spacer()
                    .padding(.horizontal)
                TextFieldWithHint(text: $quantity, label: "Quantity")
                Picker("", selection: $quantityUnit) {
                    ForEach(QuantityUnit.allCases) { value in
                        Text(value.rawValue)
                            .tag(value)
                    }
                }
                .pickerStyle(MenuPickerStyle())
            }
        }
    }
}

struct RecipeIngredientCellReadonly: View {
    @State var recipeIngredient: IngredientWithQuantity
    
    var body: some View {
        HStack{
            Text(recipeIngredient.ingredient.name)
                .font(.system(size: 14))
                .foregroundStyle(.gray)
            Spacer()
            Text("\(String.formattedQuantity(recipeIngredient.quantity)) \(recipeIngredient.quantityUnit.rawValue)")
                .font(.system(size: 14))
                .foregroundStyle(.gray)
        }
    }
}

enum RecipeIngredientListMode {
    case readOnly
    case edit
}

