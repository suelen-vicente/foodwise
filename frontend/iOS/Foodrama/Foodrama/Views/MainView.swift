//
//  MainView.swift
//  Foodrama
//
//  Created by Suelen Vicente on 2023-12-01.
//

import SwiftUI

struct MainView: View {
    var body: some View {
        TabView {
            ShoppingListView()
                .tabItem {
                    Label("Shopping List", systemImage: "list.bullet.rectangle.portrait")
                }
            MenuCalendarView()
                .tabItem {
                    Label("Menu", systemImage: "calendar")
                }
            RecipeListView()
                .tabItem {
                    Label("Recipes", systemImage: "fork.knife")
                }
            MyFridgeView()
                .tabItem {
                    Label("My Fridge", systemImage: "refrigerator")
                }
            SettingsView()
                .tabItem {
                    Label("Settgings", systemImage: "gear")
                }
        }
    }
}

#Preview {
    MainView()
}
