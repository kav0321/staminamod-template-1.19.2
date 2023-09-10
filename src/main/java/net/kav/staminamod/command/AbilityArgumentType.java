package net.kav.staminamod.command;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.kav.staminamod.INIT.AbilityManager;
import net.kav.staminamod.api.AbilityCore;
import net.minecraft.command.argument.StatusEffectArgumentType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class AbilityArgumentType implements ArgumentType<AbilityCore> {
    private static final Collection<String> EXAMPLES = Arrays.asList("spooky", "effect");
    public static final DynamicCommandExceptionType INVALID_EFFECT_EXCEPTION = new DynamicCommandExceptionType(id -> Text.translatable("effect.effectNotFound", id));

    public static AbilityArgumentType statusEffect() {
        return new AbilityArgumentType();
    }

    public static AbilityCore getStatusEffect(CommandContext<ServerCommandSource> context, String name) {
        return context.getArgument(name, AbilityCore.class);
    }




    @Override
    public AbilityCore parse(StringReader reader) throws CommandSyntaxException {
        Identifier identifier = Identifier.fromCommandInput(reader);



        return AbilityManager.abiltyregister.get(AbilityManager.abilties_id.containsValue(reader));
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return ArgumentType.super.listSuggestions(context, builder);
    }

    @Override
    public Collection<String> getExamples() {
        return ArgumentType.super.getExamples();
    }
}
