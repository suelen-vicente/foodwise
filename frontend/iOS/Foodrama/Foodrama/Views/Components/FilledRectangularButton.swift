//
//  FilledRectangularButton.swift
//  Foodrama
//
//  Created by Suelen Vicente on 2023-12-06.
//

import SwiftUI

struct FilledRectangularButton: View {
    @State var action: () -> () = { }
    @State var iconName: String = ""
    @State var text: String = ""
    
    var body: some View {
        Button{
            action()
        }label: {
            HStack {
                Image(systemName: iconName)
                    .resizable()
                    .frame(width: 30, height: 30)
                    .foregroundColor(.white)
                Text(text)
                    .foregroundColor(.white)
                    .font(.system(size: 14))
            }
            .padding()
            .background(Color.orange)
            .cornerRadius(8)
                
        }
    }
}

#Preview {
    FilledRectangularButton()
}
