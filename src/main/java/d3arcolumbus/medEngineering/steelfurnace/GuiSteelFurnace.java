package d3arcolumbus.medEngineering.steelfurnace;

import d3arcolumbus.medEngineering.MedEngineering;
import d3arcolumbus.medEngineering.container.DemoContainerSteelFurnace;
import d3arcolumbus.medEngineering.tile.DemoTileSteelFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class GuiSteelFurnace extends GuiContainer {

    public static final int WIDTH = 176;
    public static final int HEIGHT = 166;

    private static final ResourceLocation background = new ResourceLocation(MedEngineering.MODID, "textures/gui/guisteelfurnace.png");

    public GuiSteelFurnace(TileSteelFurnace tileEntity, ContainerSteelFurnace container) {
        super(container);
        xSize = WIDTH;
        ySize = HEIGHT;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
