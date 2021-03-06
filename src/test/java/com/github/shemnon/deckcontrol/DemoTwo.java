/*
 * Copyright (c) 2012, Danno Ferrin
 *   All rights reserved.
 *
 *   Redistribution and use in source and binary forms, with or without
 *   modification, are permitted provided that the following conditions are met:
 *       * Redistributions of source code must retain the above copyright
 *         notice, this list of conditions and the following disclaimer.
 *       * Redistributions in binary form must reproduce the above copyright
 *         notice, this list of conditions and the following disclaimer in the
 *         documentation and/or other materials provided with the distribution.
 *       * Neither the name of Danno Ferrin nor the
 *         names of contributors may be used to endorse or promote products
 *         derived from this software without specific prior written permission.
 *
 *   THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *   ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *   WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *   DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 *   DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *   (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *   LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *   ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *   (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *   SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.github.shemnon.deckcontrol;

import com.javafx.experiments.scenicview.ScenicView;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SceneBuilder;
import javafx.scene.control.*;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created with IntelliJ IDEA.
 * User: shemnon
 * Date: 27 Aug 2012
 * Time: 8:05 PM
 */
public class DemoTwo extends Application {
    @Override
    public void start(final Stage stage) throws Exception {
        final Deck deck = createDeck();
        final ToggleGroup alignGroup = new ToggleGroup();

        final ComboBox<String> cssCombo;

        stage.setScene(SceneBuilder.create()
                .root(
                        VBoxBuilder.create()
                                .fillWidth(false)
                                .children(new Node[]{deck, HBoxBuilder.create().children(
                                        ButtonBuilder.create()
                                                .text("<<")
                                                .onAction(actionEvent -> deck.previousNode()).build(),
                                        ButtonBuilder.create()
                                                .text(">>")
                                                .onAction(actionEvent -> deck.nextNode()).build()
                                ).build(), cssCombo = ComboBoxBuilder.create(String.class)
                                        .items(FXCollections.observableArrayList(
                                                "Slide.css",
                                                "Pile.css",
                                                "Shift.css",
                                                "Fade.css",
                                                "Shelf.css",
                                                "ShelfFlat.css",
                                                "ShelfTight.css",
                                                "ShelfInverse.css"
                                        ))
                                        .editable(true)
                                        .build(), HBoxBuilder.create().children(
                                        ToggleButtonBuilder.create()
                                                .text("NW")
                                                .toggleGroup(alignGroup)
                                                .onAction(actionEvent -> deck.setAlignment(Pos.TOP_LEFT)).build(),
                                        ToggleButtonBuilder.create()
                                                .text("N")
                                                .toggleGroup(alignGroup)
                                                .onAction(actionEvent -> deck.setAlignment(Pos.TOP_CENTER)).build(),
                                        ToggleButtonBuilder.create()
                                                .text("NE")
                                                .toggleGroup(alignGroup)
                                                .onAction(actionEvent -> deck.setAlignment(Pos.TOP_RIGHT)).build()
                                ).build(), HBoxBuilder.create().children(
                                        ToggleButtonBuilder.create()
                                                .text("W")
                                                .toggleGroup(alignGroup)
                                                .onAction(actionEvent -> deck.setAlignment(Pos.CENTER_LEFT)).build(),
                                        ToggleButtonBuilder.create()
                                                .text("C")
                                                .toggleGroup(alignGroup)
                                                .onAction(actionEvent -> deck.setAlignment(Pos.CENTER)).build(),
                                        ToggleButtonBuilder.create()
                                                .text("E")
                                                .toggleGroup(alignGroup)
                                                .onAction(actionEvent -> deck.setAlignment(Pos.CENTER_RIGHT)).build()
                                ).build(), HBoxBuilder.create().children(
                                        ToggleButtonBuilder.create()
                                                .text("BW")
                                                .toggleGroup(alignGroup)
                                                .onAction(actionEvent -> deck.setAlignment(Pos.BASELINE_LEFT)).build(),
                                        ToggleButtonBuilder.create()
                                                .text("B")
                                                .toggleGroup(alignGroup)
                                                .onAction(actionEvent -> deck.setAlignment(Pos.BASELINE_CENTER)).build(),
                                        ToggleButtonBuilder.create()
                                                .text("BE")
                                                .toggleGroup(alignGroup)
                                                .onAction(actionEvent -> deck.setAlignment(Pos.BASELINE_RIGHT)).build()
                                ).build(), HBoxBuilder.create().children(
                                        ToggleButtonBuilder.create()
                                                .text("SW")
                                                .toggleGroup(alignGroup)
                                                .onAction(actionEvent -> deck.setAlignment(Pos.BOTTOM_LEFT)).build(),
                                        ToggleButtonBuilder.create()
                                                .text("S")
                                                .toggleGroup(alignGroup)
                                                .onAction(actionEvent -> deck.setAlignment(Pos.BOTTOM_CENTER)).build(),
                                        ToggleButtonBuilder.create()
                                                .text("SE")
                                                .toggleGroup(alignGroup)
                                                .onAction(actionEvent -> deck.setAlignment(Pos.BOTTOM_RIGHT)).build()
                                ).build(), HBoxBuilder.create().children(
                                        ToggleButtonBuilder.create()
                                                .text("Fill")
                                                .toggleGroup(alignGroup)
                                                .selected(true)
                                                .onAction(actionEvent -> deck.setAlignment(null)).build(),
                                        ButtonBuilder.create()
                                                .text("ScenicView")
                                                .onAction(actionEvent -> ScenicView.show(stage.getScene())).build()
                                ).build()}).build())
                .width(350)
                .height(350)
                .build());

        cssCombo.getSelectionModel().selectedItemProperty().addListener((observableValue, oldStyle, newStyle) ->
            stage.getScene().getStylesheets().setAll(newStyle)
        );
        cssCombo.getSelectionModel().selectFirst();

        stage.setWidth(350);
        stage.setHeight(350);

        stage.show();
    }

    private Deck createDeck() {
        final Deck deck = new Deck();
        deck.getNodes().add(createTestNode("First"));
        deck.getNodes().add(createTestNode("Second"));
        deck.getNodes().add(createTestNode("Third"));
        deck.getNodes().add(createTestNode("Fourth"));
        deck.getNodes().add(createTestNode("Fifth"));
        deck.getNodes().add(createTestNode("Sixth"));
        deck.getNodes().add(createTestNode("Seventh"));
        deck.getNodes().add(createTestNode("Eighth"));
        deck.getNodes().add(createTestNode("Ninth"));
        deck.getNodes().add(createTestNode("Tenth"));
        deck.getNodes().add(createTestNode("Eleventh"));
        deck.getNodes().add(createTestNode("Twelfth"));
        deck.getNodes().add(createTestNode("Thirteenth"));
        deck.getNodes().add(createTestNode("Fourteenth"));
        deck.getNodes().add(createTestNode("Fifteenth"));
        deck.setPrimaryNodeIndex(0);
        //deck.setAlignment(Pos.BASELINE_CENTER);
        deck.getStyleClass().setAll("deck");
        return deck;
    }

    public Node createTestNode(String text) {
        Pane pane = new StackPane();
        pane.getChildren().add(new Text(text));

        int hash = text.hashCode();
        String color = "000000" + Integer.toHexString(hash & 0xffffff);
        int bottom = 10 + ((hash & 0xff) % 2) * 40;
        int right = 20 + ((hash & 0xff) % 3) * 15;
        int top = 14 + ((hash & 0xff) % 4) * 12;
        int left = 10 + ((hash & 0xff) % 5) * 10;
        color = color.substring(color.length() - 6);
        pane.setStyle("-fx-background-color: #" + color + "; -fx-padding: " + top + " " + right + " " + bottom + " " + left + ";");
        return pane;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
