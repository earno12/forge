package forge.screens.deckeditor.views;

import forge.deck.io.DeckPreferences;
import forge.game.GameType;
import forge.gui.framework.DragCell;
import forge.gui.framework.DragTab;
import forge.gui.framework.EDocID;
import forge.gui.framework.IVDoc;
import forge.itemmanager.DeckManager;
import forge.itemmanager.ItemManagerContainer;
// Import your new controller once you create it
import forge.screens.deckeditor.controllers.CDuelCommanderDecks;
import forge.screens.match.controllers.CDetailPicture;
import forge.util.Localizer;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

/** * Assembles Swing components of Duel Commander deck viewer in deck editor.
 */
public enum VDuelCommanderDecks implements IVDoc<CDuelCommanderDecks> {
    /** */
    SINGLETON_INSTANCE;

    // Fields used with interface IVDoc
    private DragCell parentCell;
    final Localizer localizer = Localizer.getInstance();

    // Change label to Duel Commander
    private final DragTab tab = new DragTab("Duel Commander");

    private DeckManager lstDecks;

    //========== Overridden methods

    @Override
    public EDocID getDocumentID() {
        // You will need to add EDITOR_DUEL_COMMANDER to the EDocID enum
        return EDocID.EDITOR_DUEL_COMMANDER;
    }

    @Override
    public DragTab getTabLabel() {
        return tab;
    }

    @Override
    public CDuelCommanderDecks getLayoutControl() {
        // Point this to your new Controller
        return CDuelCommanderDecks.SINGLETON_INSTANCE;
    }

    @Override
    public void setParentCell(DragCell cell0) {
        this.parentCell = cell0;
    }

    @Override
    public DragCell getParentCell() {
        return this.parentCell;
    }

    @Override
    public void populate() {
        // Refresh your specific controller
        CDuelCommanderDecks.SINGLETON_INSTANCE.refresh();

        // You might want to create a specific Preference for Duel Commander later
        String preferredDeck = DeckPreferences.getCommanderDeck();

        JPanel parentBody = parentCell.getBody();
        parentBody.setLayout(new MigLayout("insets 5, gap 0, wrap, hidemode 3"));
        parentBody.add(new ItemManagerContainer(lstDecks), "push, grow");
        VAllDecks.editPreferredDeck(lstDecks, preferredDeck);
    }

    //========== Retrieval methods
    public DeckManager getLstDecks() {
        return lstDecks;
    }

    public void setCDetailPicture(final CDetailPicture cDetailPicture) {
        // If this is null, that's why you get the crash
        this.lstDecks = new DeckManager(GameType.DuelCommander, cDetailPicture);
        this.lstDecks.setCaption("Duel Commander Decks");
    }
}