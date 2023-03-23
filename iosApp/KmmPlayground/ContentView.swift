//
//  ContentView.swift
//  KmmPlayground
//
//  Created by Fred Silva on 23/03/2023.
//

import SwiftUI
import SharedKit

struct ContentView: View {
    var body: some View {
        VStack {
            Text(Greeting().greet())
        }
        .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
