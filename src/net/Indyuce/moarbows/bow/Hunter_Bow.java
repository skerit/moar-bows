package net.Indyuce.moarbows.bow;

import org.bukkit.Effect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;

import net.Indyuce.moarbows.api.ArrowData;
import net.Indyuce.moarbows.api.MoarBow;
import net.Indyuce.moarbows.api.modifier.DoubleModifier;
import net.Indyuce.moarbows.api.util.LinearValue;

public class Hunter_Bow extends MoarBow {
	public Hunter_Bow() {
		super(new String[] { "Arrows deal &c{extra}% &7additional", "damage to friendly mobs." }, 0, "redstone:255,0,0", new String[] { "CHICKEN,BEEF,CHICKEN", "BEEF,BOW,BEEF", "CHICKEN,BEEF,CHICKEN" });

		addModifier(new DoubleModifier("cooldown", new LinearValue(0, 0)), new DoubleModifier("extra", new LinearValue(75, 25)));
	}

	@Override
	public boolean canShoot(EntityShootBowEvent event, ArrowData data) {
		return true;
	}

	@Override
	public void whenHit(EntityDamageByEntityEvent event, ArrowData data, Entity target) {
		if (target instanceof Monster || !(target instanceof LivingEntity))
			return;

		event.setDamage(event.getDamage() * (1 + data.getDouble("extra") / 100));
		target.getWorld().playEffect(target.getLocation(), Effect.STEP_SOUND, 55);
	}

	@Override
	public void whenLand(ArrowData data) {
	}
}
