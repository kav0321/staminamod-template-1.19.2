package net.kav.staminamod.Abilities.dodge;

import net.kav.staminamod.api.AbilityCore;
import net.kav.staminamod.api.dodging;
import net.kav.staminamod.api.multiple_animations;
import net.kav.staminamod.config.ModConfigs;
import net.kav.staminamod.data.sword_dashData;
import net.kav.staminamod.util.IEntityDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.*;
import static java.lang.Math.PI;

public class dodge extends AbilityCore implements multiple_animations {

    public Map<dodging,String> direction= new HashMap<>();
    public dodge(int cooldown, int stamina, int ID, String animationname, Text name, Text description, String filename, float speed, boolean head, boolean body, boolean righthand, boolean lefthand, boolean rightleg, boolean leftleg) {
        super(cooldown, stamina, ID, animationname, name, description, filename, speed, head, body, righthand, lefthand, rightleg, leftleg);
        direction.put(dodging.FORWARD,"forward");
        direction.put(dodging.BACKWARD,"backward");
        direction.put(dodging.LEFT,"left");
        direction.put(dodging.RIGHT,"right");
    }

    @Override
    public void tick(PlayerEntity player) {

    }

    @Override
    public void ClientSideExecution() {



        double x= MinecraftClient.getInstance().player.getVelocity().getX();
        double z=MinecraftClient.getInstance().player.getVelocity().getZ();

        double x1=x*1000000;
        double z1=z*1000000;
        int xd= (int) x1;
        int zd= (int) z1;

        int[] arr=new int[2];
        arr[0]=xd;
        arr[1]=zd;


        double xdd=(arr[0])/100000;
        double zdd=(arr[1])/100000;
        //MinecraftClient.getInstance().player.setMovementSpeed(1000);

        //System.out.println(x+" "+z);
        if((abs(x)<0.1&&abs(z)<0.1))
        {
            xdd=(arr[0]*0.2)/10000;
            zdd=(arr[1]*0.2)/10000;
        }

        double alpha = 0;
        double atan = atan(abs(xdd / zdd));
        if(xdd>=0 && zdd>=0)
        {
            alpha=2*PI- atan;
        }
        else if(xdd<0&& zdd>=0)
        {
            alpha= atan;
        }
        else if(xdd<0&& zdd<0)
        {
            alpha=PI- atan;
        }
        else if(xdd>=0&& zdd<0)
        {
            alpha=PI+ atan;
        }
        double xfinal=2*sin(-alpha);
        double zfinal=2*cos(-alpha);

        MinecraftClient.getInstance().player.setVelocity(new Vec3d(xfinal* ModConfigs.dodge_range,0,zfinal*ModConfigs.dodge_range));
    }

    @Override
    public void ServerSideExecution(MinecraftServer server, ServerPlayerEntity player) {

        double x= player.getVelocity().getX();
        double z=player.getVelocity().getZ();

        double x1=x*1000000;
        double z1=z*1000000;
        int xd= (int) x1;
        int zd= (int) z1;

        int[] arr=new int[2];
        arr[0]=xd;
        arr[1]=zd;


        double xdd=(arr[0])/100000;
        double zdd=(arr[1])/100000;

        if((abs(x)<0.1&&abs(z)<0.1))
        {
            xdd=(arr[0]*0.2)/10000;
            zdd=(arr[1]*0.2)/10000;
        }

        double alpha = 0;
        double atan = atan(abs(xdd / zdd));
        if(xdd>=0 && zdd>=0)
        {
            alpha=2*PI- atan;
        }
        else if(xdd<0&& zdd>=0)
        {
            alpha= atan;
        }
        else if(xdd<0&& zdd<0)
        {
            alpha=PI- atan;
        }
        else if(xdd>=0&& zdd<0)
        {
            alpha=PI+ atan;
        }
        double xfinal=2*sin(-alpha);
        double zfinal=2*cos(-alpha);

        player.setVelocity(new Vec3d(xfinal*ModConfigs.dodge_range,0,zfinal*ModConfigs.dodge_range));
    }

    @Override
    public boolean conditions(PlayerEntity player) {
        if(MinecraftClient.getInstance().player.isOnGround() && sword_dashData.getLevel(((IEntityDataSaver) player))==false)
        {

            return super.conditions(player);
        }
       return false;
    }


    @Override
    public String getanimation_number() {
        //System.out.println( MinecraftClient.getInstance().player.forwardSpeed);//back is neg
        //System.out.println( MinecraftClient.getInstance().player.sidewaysSpeed);//right is neg
        //  MinecraftClient.getInstance().player.setVelocity(new Vec3d(xfinal,0,zfinal));
        boolean forward= MinecraftClient.getInstance().player.forwardSpeed>0 &&MinecraftClient.getInstance().player.sidewaysSpeed==0;
        boolean left= MinecraftClient.getInstance().player.sidewaysSpeed>0 &&MinecraftClient.getInstance().player.forwardSpeed==0;
        boolean south= MinecraftClient.getInstance().player.forwardSpeed<0 &&MinecraftClient.getInstance().player.sidewaysSpeed==0;
        boolean right= MinecraftClient.getInstance().player.sidewaysSpeed<0 &&MinecraftClient.getInstance().player.forwardSpeed==0;
        if(forward)
        {
            return direction.get(dodging.FORWARD)+animationname;
        }
        if(south)
        {
            return direction.get(dodging.BACKWARD)+animationname;
        }
        if(left)
        {
            return direction.get(dodging.LEFT)+animationname;
        }
        if(right)
        {
            return direction.get(dodging.RIGHT)+animationname;
        }
        return direction.get(dodging.FORWARD)+animationname;
    }
}
