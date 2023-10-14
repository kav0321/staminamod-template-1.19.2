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
    public record AbilityAni(int playerId, int index) {
        public static Identifier ID = new Identifier(StaminaMod.MODID, "attack_an");





        public PacketByteBuf write() {
            PacketByteBuf buffer = PacketByteBufs.create();
            buffer.writeInt(playerId);

            buffer.writeInt(index);

            return buffer;
        }

        public static AbilityAni read(PacketByteBuf buffer) {
            int playerId = buffer.readInt();

            int index = buffer.readInt();

            return new AbilityAni(playerId,index);
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
}
