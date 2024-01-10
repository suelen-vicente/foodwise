//
//  ColapsableTitle.swift
//  Foodrama
//
//  Created by Suelen Vicente on 2023-12-04.
//

import SwiftUI

struct ColapsableTitle: View {
    @Binding var show: Bool
    @State var title: String = "Test"
    
    var body: some View {
        Button{
            show.toggle()
        }label: {
            HStack(alignment: .center){
                Text(title)
                    .font(.system(size: 14, weight: .bold))
                    .foregroundStyle(.black)
                Spacer()
                Image(systemName: show ? "chevron.up" : "chevron.down")
            }
        }
    }
}
