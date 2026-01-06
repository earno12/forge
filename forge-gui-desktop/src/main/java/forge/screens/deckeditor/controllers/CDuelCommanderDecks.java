package forge.screens.deckeditor.controllers;

import forge.deck.DeckProxy;
import forge.gui.framework.ICDoc;
import forge.itemmanager.DeckManager;
import forge.screens.deckeditor.views.VDuelCommanderDecks;

/**
 * Controls the "Duel Commander Decks" panel in the deck editor UI.
 */
public enum CDuelCommanderDecks implements ICDoc {
    SINGLETON_INSTANCE;

    // CRITICAL: Point this to your new Duel Commander view
    private final VDuelCommanderDecks view = VDuelCommanderDecks.SINGLETON_INSTANCE;

    @Override
    public void register() {
    }

    @Override
    public void initialize() {
        refresh();
    }

    public void refresh() {
        // Get the manager from the view
        DeckManager dm = view.getLstDecks();

        // ADD THIS GUARD: If the UI isn't built yet, dm is null.
        // Just exit; the framework will call this again later.
        if (dm == null) {
            return;
        }

        CAllDecks.refreshDeckManager(dm, DeckProxy.getAllDuelCommanderDecks());
    }

    @Override
    public void update() {
        CAllDecks.updateDeckManager(view.getLstDecks());
    }
}