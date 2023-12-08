//
//  TextFieldWithHint.swift
//  Foodrama
//
//  Created by Suelen Vicente on 2023-12-06.
//

import SwiftUI

struct TextFieldWithHint: View {
    @Binding var text: String
    var validation: (String) -> Bool = { _ in return true }
    var label: String = ""
    var hint: String = ""
    var placeholder: String = ""
    var keyboardType: UIKeyboardType = .default
    var lineLimit: Int = 1
    
    @State private var isValid: Bool = true
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(label)
                .font(.system(size: 14, weight: .bold))
                .foregroundColor(.black)
            HStack {
                TextField(placeholder, text: $text, axis: .vertical)
                    .lineLimit(1...lineLimit)
                    .font(.system(size: 14))
                    .keyboardType(keyboardType)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                if !isValid {
                    Image(systemName: "xmark.circle.fill")
                        .foregroundColor(.orange)
                }
            }
            if !isValid {
                Text(hint)
                    .font(.caption)
                    .foregroundColor(isValid ? .gray : .orange)
            }
        }.onChange(of: text){
             isValid = text.isEmpty || validation(text)
         }
    }
}
