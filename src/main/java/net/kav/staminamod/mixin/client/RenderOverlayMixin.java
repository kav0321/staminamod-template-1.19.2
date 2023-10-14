package net.kav.staminamod.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.kav.staminamod.INIT.AbilityManager;
import net.kav.staminamod.StaminaMod;
import net.kav.staminamod.data.Equipdata;
import net.kav.staminamod.data.StaminaData;
import net.kav.staminamod.event.client.client_tick;
import net.kav.staminamod.util.GlobalStamina;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class RenderOverlayMixin extends DrawableHelper {
    private static final Identifier Stamina_StartFull= new Identifier(StaminaMod.MODID,"textures/gui/stamina/stamina_full.png");
    private static final Identifier slot = new Identifier(StaminaMod.MODID,"textures/gui/slot/slot.png");
    private static Identifier slot1 = new Identifier(StaminaMod.MODID,"textures/gui/slot/empty.png");
    private static  Identifier slot2 = new Identifier(StaminaMod.MODID,"textures/gui/slot/empty.png");
    private static  Identifier slot3 = new Identifier(StaminaMod.MODID,"textures/gui/slot/empty.png");
    private static  Identifier cooldown = new Identifier(StaminaMod.MODID,"textures/gui/cooldown/cooldown.png");
    private static final Identifier Stamina_StartEmpty= new Identifier(StaminaMod.MODID,"textures/gui/stamina/stamina_empty.png");
    //private static final Identifier COOLDOWN= new Identifier(Kav_soul_like.MOD_ID,"textures/gui/cooldown.png");
   // private static final Identifier WIDGETS_TEXTURE = new Identifier("textures/gui/widgets.png");
   // private static final Identifier TEXT= new Identifier(Kav_soul_like.MOD_ID,"textures/gui/text/texts.png");
    @Inject(at = @At("TAIL"), method = "renderStatusBars")
    private void renderStatusBars(CallbackInfo info) {
        MatrixStack matrixStack = new MatrixStack();
        MinecraftClient client = MinecraftClient.getInstance();
        int scaledWidth = client.getWindow().getScaledWidth();
        int scaledHeight = client.getWindow().getScaledHeight();
        int x =0;
        int y= 0;
        if(client!=null)
        {
            int width =client.getWindow().getScaledWidth();
            int heigth =client.getWindow().getScaledHeight();
            x=width/2;
            y=heigth;
        }
        Ability(matrixStack,client,x,y);
        if(!client.player.isCreative() && !client.player.isSpectator() &&!client.player.isSubmergedInWater())
            StaminaBar(matrixStack,client,x,y);



    }

    private void draw(Identifier identifier, MatrixStack matrixStack, int x, int y, int positionx, int positiony, int v)
    {

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0, identifier);
        DrawableHelper.drawTexture(matrixStack,x-positionx,y-positiony,0,0,v,42,5,42,35);


    }
    private void draw(Identifier identifier, MatrixStack matrixStack, int x, int y, int positionx, int positiony, int v,int width,int height,int textureWidth,int textureHeight)
    {

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0, identifier);
        DrawableHelper.drawTexture(matrixStack,x-positionx,y-positiony,0,0,v,width,height,textureWidth,textureHeight);


    }
    private void draw(Identifier identifier, MatrixStack matrixStack, int x, int y, int positionx, int positiony, int u,int v,int width,int height,int textureWidth,int textureHeight)
    {

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0, identifier);
        DrawableHelper.drawTexture(matrixStack,x-positionx,y-positiony,0,u,v,width,height,textureWidth,textureHeight);


    }
    private void draw(Identifier identifier,MatrixStack matrixStack,int x, int y, int x1, int y1, int v,int width)
    {


        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, identifier);

        DrawableHelper.drawTexture(matrixStack, x - x1, y - y1, 0, 0, v, width, 5, 42, 35);

    }
    private void drawscale(Identifier identifier,MatrixStack matrixStack,int x, int y, int x1, int y1, int v)
    {


        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, identifier);

        DrawableHelper.drawTexture(matrixStack, x - x1, y - y1, 0, 0, v, 16, 16, 16, 16);

    }
    public void drawTexture(MatrixStack matrices, int x, int y, int u, int v, int width, int height) {
        DrawableHelper.drawTexture(matrices, x, y,1 , u, v, width, height, 256, 256);
    }
    public void drawTextures(MatrixStack matrices, int x, int y, int u, int v, int width, int height) {
        DrawableHelper.drawTexture(matrices, x, y,1 , u, v, width, height, 32, 32);
    }
    private void Ability(MatrixStack matrixStack, MinecraftClient client, int x, int y)
    {
        float percentage;
        float percentage2;
        float percentage3;
        int resolution =20;
        int pixel;
        int pixel2;
        int pixel3;
       if(Equipdata.getability( (IEntityDataSaver) MinecraftClient.getInstance().player,"ability1")!=0)
       {

           percentage = (client_tick.getTick1*100)/AbilityManager.abiltyregister.get(Equipdata.getability( (IEntityDataSaver) MinecraftClient.getInstance().player,"ability1")).cooldown;
           //System.out.println(percentage);
           if(percentage==100)
           {
               percentage=0;
           }
           pixel= (int) (percentage*resolution)/100;

           slot1= AbilityManager.abiltyregister.get(Equipdata.getability( (IEntityDataSaver) MinecraftClient.getInstance().player,"ability1")).file;
       }
       else
       {
           pixel=0;
           slot1=  new Identifier(StaminaMod.MODID,"textures/gui/slot/empty.png");
       }
        if(Equipdata.getability( (IEntityDataSaver) MinecraftClient.getInstance().player,"ability2")!=0)
        {
            percentage2 = (client_tick.getTick2*100)/AbilityManager.abiltyregister.get(Equipdata.getability( (IEntityDataSaver) MinecraftClient.getInstance().player,"ability2")).cooldown;
           // System.out.println(percentage2);
            if(percentage2==100)
            {
                percentage2=0;
            }
            pixel2= (int) (percentage2*resolution)/100;
           slot2= AbilityManager.abiltyregister.get(Equipdata.getability( (IEntityDataSaver) MinecraftClient.getInstance().player,"ability2")).file;
        }
        else
        {
            pixel2=0;
            slot2=  new Identifier(StaminaMod.MODID,"textures/gui/slot/empty.png");
        }
        if(Equipdata.getability( (IEntityDataSaver) MinecraftClient.getInstance().player,"ability3")!=0)
        {
            percentage3 = (client_tick.getTick3*100)/AbilityManager.abiltyregister.get(Equipdata.getability( (IEntityDataSaver) MinecraftClient.getInstance().player,"ability3")).cooldown;
           // System.out.println(percentage3);
            if(percentage3==100)
            {
                percentage3=0;
            }
            pixel3= (int) (percentage3*resolution)/100;
            slot3= AbilityManager.abiltyregister.get(Equipdata.getability( (IEntityDataSaver) MinecraftClient.getInstance().player,"ability3")).file;
        }
        else
        {
            pixel3=0;
            slot3=  new Identifier(StaminaMod.MODID,"textures/gui/slot/empty.png");
        }

        int initial=140;
        this.draw(slot1, matrixStack, x, y, initial+resolution*2+4, 20,0,resolution,resolution,resolution,resolution);

        this.draw(slot2, matrixStack, x, y, initial+resolution+2, 20,0,resolution,resolution,resolution,resolution);

        this.draw(slot3, matrixStack, x, y, initial, 20,0,resolution,resolution,resolution,resolution);




        this.draw(cooldown, matrixStack, x, y, initial+resolution*2+4, 20,0,(0+pixel),resolution,resolution,resolution,resolution*2);

        this.draw(cooldown, matrixStack, x, y, initial+resolution+2, 20,0,(0+pixel2),resolution,resolution,resolution,resolution*2);

        this.draw(cooldown, matrixStack, x, y, initial, 20,0,(0+pixel3),resolution,resolution,resolution,resolution*2);


        this.draw(slot, matrixStack, x, y, initial+resolution*2+4, 20,0,resolution,resolution,resolution,resolution);

        this.draw(slot, matrixStack, x, y, initial+resolution+2, 20,0,resolution,resolution,resolution,resolution);

        this.draw(slot, matrixStack, x, y, initial, 20,0,resolution,resolution,resolution,resolution);



    }
    private void StaminaBar(MatrixStack matrixStack, MinecraftClient client, int x, int y)
    {
        //get stamina and maxstamina
        float stamina = StaminaData.getSTAMINA(((IEntityDataSaver) MinecraftClient.getInstance().player));
        float maximumsta =StaminaData.getMAXSTAMINA(((IEntityDataSaver) MinecraftClient.getInstance().player));

        //won't appear if the player is increative, spectator or is riding an animal
        if(!(client.player.isCreative())&&!(client.player.isSpectator()) && !client.player.hasVehicle())
        {

            //when increase the bar length, it will increase by a certain pixel in y axis. The Stamina will be divided by 5 to calculate how many bars need to render. If the bar is not a multiple of 5, it will calulate the reminder and depending on the number of the reminder, the extra bar finishing bar length will be different
            int number_of_bar;
            int number_of_bars_reminder;
            int numbers_of_bar_before;
            int extra_bar;




            number_of_bars_reminder  = (int) maximumsta %5;

            if(number_of_bars_reminder!=0)
            {
                extra_bar=1;
            }
            else
                extra_bar =0;

            numbers_of_bar_before= (int) maximumsta/5;
            number_of_bar= extra_bar+numbers_of_bar_before;

            int initialx= GlobalStamina.X;
            int initialy=GlobalStamina.Y;

            this.draw(Stamina_StartEmpty,matrixStack,x,y,initialx,initialy,0);

            int pixel_offset=0;
            for(int i=0;i< number_of_bar - 2;i++)
            {
                pixel_offset=10+10*(i+1);//offset for the finishing bar-> 10 pixels for starts and add 10 far each bar without finishing bar
                this.draw(Stamina_StartEmpty, matrixStack, x, y, initialx-10-10*i, initialy,5);
            }

            int z;
            if(number_of_bars_reminder==1)
            {
                this.draw(Stamina_StartEmpty, matrixStack, x, y, initialx-pixel_offset, initialy,10);

            }
            else if(number_of_bars_reminder==2)
            {
                this.draw(Stamina_StartEmpty, matrixStack, x, y, initialx-pixel_offset, initialy,15);
            }
            else if(number_of_bars_reminder==3)
            {
                this.draw(Stamina_StartEmpty, matrixStack, x, y, initialx-pixel_offset, initialy,20);
            }
            else if(number_of_bars_reminder==4)
            {
                this.draw(Stamina_StartEmpty, matrixStack, x, y, initialx-pixel_offset, initialy,25);
            }
            else if(number_of_bars_reminder==0)
            {
                this.draw(Stamina_StartEmpty, matrixStack, x, y, initialx-pixel_offset, initialy,30);
            }





            int stamina_int=0;
            int stamina_rendering;
            stamina_int = (int) (stamina);
            if(stamina_int<=0)
            {
                stamina_int=0;

            }

            stamina_rendering=32+2*stamina_int;//start rendering at 32 pixel width
            if(stamina_rendering>42)
            {
                stamina_rendering=42;//maxwidth is 42
            }

            //Draw full stamina
            if(stamina_int!=0)
            {
                this.draw(Stamina_StartFull, matrixStack, x, y, initialx, initialy,0,stamina_rendering);
            }






            for (int i = 0; i < number_of_bar - 2; i++) {


                stamina_int =  ((int)stamina-(5+i*5));
                if(stamina_int<0)
                {
                    stamina_int=0;
                }

                stamina_rendering=32+2*stamina_int;
                if(stamina_rendering>42)
                {
                    stamina_rendering=42;
                }


                pixel_offset=10+10*(i+1);

                this.draw(Stamina_StartFull, matrixStack, x, y, initialx-10-10*i, initialy,5,stamina_rendering);

            }



            stamina_int = (int) (stamina-(maximumsta-5));
            stamina_rendering=32+2*stamina_int;
            if(stamina_int>5)
            {
                stamina_int=5;
            }
            if(stamina_int<0)
            {
                stamina_int=0;
            }
            if(stamina_rendering>42)
            {
                stamina_rendering=42;
            }

            //client.player.sendMessage(Text.of(Integer.toString(number_of_bars_reminder)),false);
            if (number_of_bars_reminder == 1) {
                this.draw(Stamina_StartFull, matrixStack, x, y, initialx-pixel_offset, initialy,10,stamina_rendering-7);

            } else if (number_of_bars_reminder == 2) {
                this.draw(Stamina_StartFull, matrixStack, x, y, initialx-pixel_offset, initialy,15,stamina_rendering-5);
            } else if (number_of_bars_reminder == 3) {
                this.draw(Stamina_StartFull, matrixStack, x, y, initialx-pixel_offset, initialy,20,stamina_rendering-4);

            } else if (number_of_bars_reminder == 4) {
                this.draw(Stamina_StartFull, matrixStack, x, y, initialx-pixel_offset, initialy,25,stamina_rendering-2);
            } else  {
                this.draw(Stamina_StartFull, matrixStack, x, y, initialx-pixel_offset, initialy,30,stamina_rendering);
            }






        }
    }
}
