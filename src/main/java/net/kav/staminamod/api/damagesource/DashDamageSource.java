package net.kav.staminamod.api.damagesource;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.EntityDamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class DashDamageSource extends EntityDamageSource {
    public DashDamageSource(String name, Entity source) {
        super(name, source);
    }
    @Override
    public Text getDeathMessage(LivingEntity entity) {
        ItemStack itemStack = this.source instanceof LivingEntity ? ((LivingEntity)this.source).getMainHandStack() : ItemStack.EMPTY;
        String string = "death.dash." + this.name;
        if (!itemStack.isEmpty() && itemStack.hasCustomName()) {
            return Text.translatable(string + ".item", entity.getDisplayName(), this.source.getDisplayName(), itemStack.toHoverableText());
        }
        return Text.translatable(string, entity.getDisplayName(), this.source.getDisplayName());
    }
}
