//
//  SaveOrCancelBar.swift
//  Foodrama
//
//  Created by Suelen Vicente on 2023-12-08.
//

import SwiftUI

struct SaveOrCancelBar: View {
    var cancel: () -> Void = {}
    var save: () -> Void = {}
    
    var body: some View {
        HStack{
            Spacer()
            FilledRectangularButton(action: cancel, iconName: "x.circle", text: "Cancel", color: .gray)
            Spacer()
            FilledRectangularButton(action: save, iconName: "checkmark.circle", text: "Save")
            Spacer()
        }
    }
}

#Preview {
    SaveOrCancelBar()
}
