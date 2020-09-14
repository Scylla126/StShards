
package com.shepherdjerred.stshards.utilities;

import com.shepherdjerred.stshards.Main;
import com.shepherdjerred.stshards.messages.MessageHelper;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;


public class ItemHelper {

	// Gets a colored string of the Shard's lore
	public static List<String> getShardLore() {

		return MessageHelper.colorConfigListStrings("item.lore");

	}

	// Gets a colored string of the Shard's name
	public static String getShardName() {

		return MessageHelper.colorConfigString("item.name");

	}

	// Gets a colored string of the Shard's material
	public static Material getShardMaterial() {

		return Material.getMaterial(Main.getInstance().getConfig().getString("item.material"));

	}

	// Gets an ItemStack of the Shard
	public static ItemStack getShardItemStack() {

		ItemStack shard = new ItemStack(getShardMaterial());

		ItemMeta shardMeta = shard.getItemMeta();

		shardMeta.setDisplayName(getShardName());
		shardMeta.setLore(getShardLore());

		shard.setItemMeta(shardMeta);

		return shard;

	}

	// Check if the given ItemStack is the right material to be upgraded
	public static boolean canUseShards(ItemStack item) {

		for (String itemGroup : Main.getInstance().getConfig().getConfigurationSection("stats.items").getKeys(false)) {

			for (String itemInGroup : Main.getInstance().getConfig().getStringList("stats.items." + itemGroup + ".materials")) {

				if (item.getType() == Material.getMaterial(itemInGroup)) {

					return true;

				}

			}

		}

		return false;

	}
	
	public static String getItemGroup(ItemStack item) {
		
		for (String itemGroup : Main.getInstance().getConfig().getConfigurationSection("stats.items").getKeys(false)) {

			for (String itemInGroup : Main.getInstance().getConfig().getStringList("stats.items." + itemGroup + ".materials")) {

				if (item.getType() == Material.getMaterial(itemInGroup)) {

					return itemGroup;

				}

			}

		}

		return null;
		
	}

	// Gets the item lore for the given tier
	public static List<String> getShardTierLore(String tier) {

		return MessageHelper.colorConfigListStrings("upgrades.tiers." + tier + ".lore");

	}

	// Gets the tier for the given item lore
	public static String getTierFromItemLore(ItemStack item) {
		// Loop through the tiers
		for (String tier : Main.getInstance().getConfig().getConfigurationSection("upgrades.tiers").getKeys(false)) {
			// Check if the item in hand equals the tier lore
			if (item.hasItemMeta() && item.getItemMeta().hasLore() && item.getItemMeta().getLore().equals(getShardTierLore(tier))) {
				return tier;
			}
		}
		return null;
	}
}
