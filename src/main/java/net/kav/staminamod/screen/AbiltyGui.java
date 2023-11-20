package net.kav.staminamod.screen;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Color;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.util.TriState;
import net.kav.staminamod.INIT.AbilityManager;
import net.kav.staminamod.StaminaMod;
import net.kav.staminamod.api.AbilityCore;
import net.kav.staminamod.data.AbilityData;
import net.kav.staminamod.data.Equipdata;
import net.kav.staminamod.networking.ModMessages;
import net.kav.staminamod.networking.packet.Packets;
import net.kav.staminamod.screen.buttons.arrow_button;
import net.kav.staminamod.screen.panels.AbilityPanel;
import net.kav.staminamod.screen.togels.slotselect;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import software.bernie.shadowed.eliotlash.mclib.math.functions.limit.Min;

import static io.github.cottonmc.cotton.gui.client.BackgroundPainter.createLightDarkVariants;
import static io.github.cottonmc.cotton.gui.client.BackgroundPainter.createNinePatch;

//shoutout to the villagerquest mod, my life got way easier with their example of gui rendering
public class AbiltyGui extends LightweightGuiDescription {
    private static Identifier slot = new Identifier(StaminaMod.MODID,"textures/gui/slot/slot.png");
    private static Identifier ability_button = new Identifier(StaminaMod.MODID,"textures/gui/ability_slot_sprite.png");
    private static Identifier[] slotselection= new Identifier[2];
    private static Identifier SLOT1 = new Identifier(StaminaMod.MODID,"textures/gui/slot/empty.png");
    private static Identifier SLOT2 = new Identifier(StaminaMod.MODID,"textures/gui/slot/empty.png");
    private static Identifier SLOT3 = new Identifier(StaminaMod.MODID,"textures/gui/slot/empty.png");

    private static int SLOT1_temp;
    private static Text description_temp = Text.translatable("");
    public static Identifier SLOT_TEMP = new Identifier(StaminaMod.MODID,"textures/gui/slot/empty.png");
    public static Identifier SLOT_TEMP_empty = new Identifier(StaminaMod.MODID,"textures/gui/slot/empty.png");
    private static final Identifier BACKGROUND= new Identifier(StaminaMod.MODID,"textures/gui/panel/panel_dark.png");
    private static final Identifier scroll_panel= new Identifier(StaminaMod.MODID,"textures/gui/panel/dark_sprite.png");
    private static final Identifier description_sprite= new Identifier(StaminaMod.MODID,"textures/gui/description_sprite.png");
    BackgroundPainter SOUL= createLightDarkVariants((createNinePatch(BACKGROUND)), createNinePatch(BACKGROUND));
    WPlainPanel root = new WPlainPanel();
    WText description_text = new WText(description_temp,Color.WHITE.toRgb());
    slotselect str= new slotselect();
    slotselect str2= new slotselect();
    slotselect str3= new slotselect();
    public AbiltyGui()
    {
        this.INIT();

        setRootPanel(root);
        root.setSize(300, 215);
        setRootPanel(root);
        root.setInsets(Insets.ROOT_PANEL);
        AbilityPanel plainPanel = new AbilityPanel();
        WScrollPanel scrollPanel = new WScrollPanel(plainPanel);
        scrollPanel.setScrollingHorizontally(TriState.FALSE);
        WSprite description = new WSprite(description_sprite);
        WSprite sprite = new WSprite(SLOT_TEMP);





        root.add(str, 1, 1,32,32);



        root.add(str2, 49, 1,32,32);



        root.add(str3, 97, 1,32,32);



//panel at the right
        WSprite panel= new WSprite(scroll_panel);
        root.add(panel, 180, 1,125,200);
        root.add(description,1,50,175,150);
        root.add(description_text,1,50,175,150);




        root.add(scrollPanel, 180, 1, 125, 200);
            int i=0;
        for(AbilityCore ability: AbilityData.getAbility((IEntityDataSaver) MinecraftClient.getInstance().player))
        {

        abilitybuttondraw(8,(i)*32+1,plainPanel, i);
            if(i+1==AbilityData.getAbility((IEntityDataSaver) MinecraftClient.getInstance().player).size()/2)
            {
                break;
            }
        i++;
        }

        str.setOnClick(()->
        {

            if(str.isEnabled())
            {

                if((str2.SLOT_TEMP!=SLOT_TEMP&&str3.SLOT_TEMP!=SLOT_TEMP)||SLOT1_temp==0)
                {
                    //System.out.println("clock");
                    str.SLOT_TEMP=SLOT_TEMP;
                    //System.out.println(SLOT1_temp+  " S");
                    Equipdata.addability((IEntityDataSaver) MinecraftClient.getInstance().player,SLOT1_temp,"ability1");

                    ClientPlayNetworking.send(ModMessages.ABILITYSYNC,new Packets.AbilitySync(SLOT1_temp,"ability1").write());



                    SLOT_TEMP= SLOT_TEMP_empty;
                    SLOT1_temp =0;
                }


            }
        });

        str2.setOnClick(()->
        {
            if(str2.isEnabled())
            {
                if((str3.SLOT_TEMP!=SLOT_TEMP&&str.SLOT_TEMP!=SLOT_TEMP)||SLOT1_temp==0)
                {
                    //System.out.println(SLOT1_temp);
                    str2.SLOT_TEMP=SLOT_TEMP;
                    //System.out.println(SLOT1_temp+  " S");
                    Equipdata.addability((IEntityDataSaver) MinecraftClient.getInstance().player,SLOT1_temp,"ability2");

                    ClientPlayNetworking.send(ModMessages.ABILITYSYNC,new Packets.AbilitySync(SLOT1_temp,"ability2").write());


                    SLOT_TEMP= SLOT_TEMP_empty;
                    SLOT1_temp =0;
                }

            }
        });
        str3.setOnClick(()->
        {

            if((str.SLOT_TEMP!=SLOT_TEMP&&str2.SLOT_TEMP!=SLOT_TEMP)||SLOT1_temp==0)
            {
               // System.out.println(SLOT1_temp);
                str3.SLOT_TEMP=SLOT_TEMP;
                //System.out.println(SLOT1_temp+  " S");
                Equipdata.addability((IEntityDataSaver) MinecraftClient.getInstance().player,SLOT1_temp,"ability3");


                ClientPlayNetworking.send(ModMessages.ABILITYSYNC,new Packets.AbilitySync(SLOT1_temp,"ability3").write());


                SLOT_TEMP= SLOT_TEMP_empty;
                SLOT1_temp =0;
            }

        });
       // root.add(sprite,(int) MinecraftClient.getInstance().mouse.getX(),(int)MinecraftClient.getInstance().mouse.getY());
       // System.out.println(AbilityData.getAbility((IEntityDataSaver) MinecraftClient.getInstance().player).get(0).ID);
    }
    private void INIT()
    {
        slotselection[0]=slot;
        if(AbilityManager.abiltyregister!=null)
        {
            if(Equipdata.getability((IEntityDataSaver) MinecraftClient.getInstance().player,"ability1")!=0)
            {
                str.SLOT_TEMP =AbilityManager.abiltyregister.get(Equipdata.getability((IEntityDataSaver) MinecraftClient.getInstance().player,"ability1")).file;
            }
            if(Equipdata.getability((IEntityDataSaver) MinecraftClient.getInstance().player,"ability2")!=0)
            {
                str2.SLOT_TEMP =AbilityManager.abiltyregister.get(Equipdata.getability((IEntityDataSaver) MinecraftClient.getInstance().player,"ability2")).file;
            }
            if(Equipdata.getability((IEntityDataSaver) MinecraftClient.getInstance().player,"ability3")!=0)
            {
                str3.SLOT_TEMP =AbilityManager.abiltyregister.get(Equipdata.getability((IEntityDataSaver) MinecraftClient.getInstance().player,"ability3")).file;
            }


        }

    }

    @Override
    public void addPainters()
    {
        if (this.root != null && !this.fullscreen) {
            this.root.setBackgroundPainter(SOUL);
        }
    }

    private void abilitybuttondraw(int x, int y,AbilityPanel panel, int j)
    {
        arrow_button ability_buttons = new arrow_button();
        panel.add(ability_buttons,x,y,32,32);

        Identifier sprite  =AbilityData.getAbility((IEntityDataSaver) MinecraftClient.getInstance().player).get(j).file;
        Text name  =AbilityData.getAbility((IEntityDataSaver) MinecraftClient.getInstance().player).get(j).name;
        WSprite ability_sprite= new WSprite(sprite);
        WText name2 = new WText(Text.translatable(name.getString()), Color.WHITE.toRgb());

       // MinecraftClient.getInstance().player.sendMessage(Text.translatable("item.staminamod.flip_attack_sword_learner"),true);

        panel.add(ability_sprite, x, y,32,32);
        panel.add(name2,x+32,y+2,64,64);
        WSprite abilityplate_sprite= new WSprite(ability_button);
        panel.add(abilityplate_sprite, x, y,105,32);

        ability_buttons.setOnClick(() -> {

            if(!str.SLOT_TEMP.equals(sprite) && !str2.SLOT_TEMP.equals(sprite)&&!str3.SLOT_TEMP.equals(sprite))
            {
                SLOT_TEMP= sprite;
                SLOT1_temp=AbilityData.getAbility((IEntityDataSaver) MinecraftClient.getInstance().player).get(j).ID;

                ability_buttons.isclick=true;
            }
            description_text.setText( AbilityData.getAbility((IEntityDataSaver) MinecraftClient.getInstance().player).get(j).description);
        });




    }

}
