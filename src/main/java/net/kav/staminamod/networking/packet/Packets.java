package net.kav.staminamod.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.kav.staminamod.StaminaMod;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class Packets {

    public record stamina_location(int x,int y) {
        public static Identifier ID = new Identifier(StaminaMod.MODID, "stamina_location");
        public PacketByteBuf write() {
            PacketByteBuf buffer = PacketByteBufs.create();
            buffer.writeInt(x);
            buffer.writeInt(y);
            return buffer;
        }

        public static stamina_location read(PacketByteBuf buffer) {
            int x = buffer.readInt();

            int y= buffer.readInt();
            return new stamina_location(x,y);
        }
    }
    public record AbilityAni(int playerId, int index,float length, String name) {






        public PacketByteBuf write() {
            PacketByteBuf buffer = PacketByteBufs.create();
            buffer.writeInt(playerId);

            buffer.writeInt(index);
            buffer.writeFloat(length);
            buffer.writeString(name);
            return buffer;
        }

        public static AbilityAni read(PacketByteBuf buffer) {
            int playerId = buffer.readInt();

            int index = buffer.readInt();
            float length = buffer.readFloat();
            String name =  buffer.readString();
            return new AbilityAni(playerId,index,length,name);
        }
    }
    public record vec3d(double x, double y, double z) {
        public PacketByteBuf write() {
            PacketByteBuf buffer = PacketByteBufs.create();
            buffer.writeDouble(x);
            buffer.writeDouble(y);
            buffer.writeDouble(z);

            return buffer;
        }

        public static vec3d read(PacketByteBuf buffer) {
            double x = buffer.readDouble();
            double y = buffer.readDouble();
            double z = buffer.readDouble();

            return new vec3d(x,y,z);
        }
    }

    public record tick_equip(int ability1, int ability2, int ability3) {
        public static Identifier ID = new Identifier(StaminaMod.MODID, "equip_tick");





        public PacketByteBuf write() {
            PacketByteBuf buffer = PacketByteBufs.create();
            buffer.writeInt(ability1);
            buffer.writeInt(ability2);
            buffer.writeInt(ability3);

            return buffer;
        }

        public static tick_equip read(PacketByteBuf buffer) {
            int ability = buffer.readInt();
            int ability2 = buffer.readInt();
            int ability3 = buffer.readInt();

            return new tick_equip(ability,ability2,ability3);
        }
    }

    public record AbilitySync(int id, String slot) {
        public static Identifier ID = new Identifier(StaminaMod.MODID, "attack_animation");





        public PacketByteBuf write() {
            PacketByteBuf buffer = PacketByteBufs.create();
            buffer.writeInt(id);

            buffer.writeString(slot);

            return buffer;
        }

        public static AbilitySync read(PacketByteBuf buffer) {
            int id = buffer.readInt();

            String slot = buffer.readString();

            return new AbilitySync(id,slot);
        }
    }


    public record ABILITY_MODCONFIG(int stamina, int cooldown) {
        public static Identifier ID0 = new Identifier(StaminaMod.MODID, "dodgea");
        public static Identifier ID90 = new Identifier(StaminaMod.MODID, "flipa");
        public static Identifier ID180 = new Identifier(StaminaMod.MODID, "foota");
        public static Identifier ID270 = new Identifier(StaminaMod.MODID, "hurricana");
        public static Identifier ID360 = new Identifier(StaminaMod.MODID, "kicka");
        public static Identifier ID450 = new Identifier(StaminaMod.MODID, "parrya");
        public static Identifier ID540= new Identifier(StaminaMod.MODID, "sword_dasha");

        public static Identifier ID630= new Identifier(StaminaMod.MODID, "guard");
        public static Identifier ID720= new Identifier(StaminaMod.MODID, "shield_crusher");
        public static Identifier ID810= new Identifier(StaminaMod.MODID, "mega_dash");

        public PacketByteBuf write() {
            PacketByteBuf buffer = PacketByteBufs.create();
            buffer.writeInt(stamina);
            buffer.writeInt(cooldown);

            return buffer;
        }

        public static ABILITY_MODCONFIG read(PacketByteBuf buffer) {
            int stamina = buffer.readInt();

            int cooldown = buffer.readInt();

            return new ABILITY_MODCONFIG(stamina,cooldown);
        }
    }

    public record parry_re(int duration, int amplitude) {
        public PacketByteBuf write() {
            PacketByteBuf buffer = PacketByteBufs.create();
            buffer.writeInt(duration);
            buffer.writeInt(amplitude);

            return buffer;
        }

        public static parry_re read(PacketByteBuf buffer) {
            int duration = buffer.readInt();

            int amplitude = buffer.readInt();

            return new parry_re(duration,amplitude);
        }
    }
    public record cooldown(int cooldown, String slot) {
        public PacketByteBuf write() {
            PacketByteBuf buffer = PacketByteBufs.create();
            buffer.writeInt(cooldown);

            buffer.writeString(slot);

            return buffer;
        }

        public static cooldown read(PacketByteBuf buffer) {
            int cooldown = buffer.readInt();

            String slot = buffer.readString();

            return new cooldown(cooldown,slot);
        }
    }
}
