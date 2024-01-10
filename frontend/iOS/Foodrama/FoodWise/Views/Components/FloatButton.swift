//
//  FloatButton.swift
//  Foodrama
//
//  Created by Suelen Vicente on 2023-12-01.
//

import SwiftUI

struct FloatAddButton: View {
    @State var action: () -> Void = { }
    
    var body: some View {
        Button {
            action()
        } label: {
            Image(systemName: "plus")
                .font(.title.weight(.semibold))
                .padding()
                .background(Color.orange)
                .foregroundColor(.white)
                .clipShape(Circle())
        }
    }
}

#Preview {
    FloatAddButton()
}
