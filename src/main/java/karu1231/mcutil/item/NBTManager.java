package karu1231.mcutil.item;

import karu1231.mcutil.MCUtil;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NBTManager {

	private static final Plugin plugin = MCUtil.plugin;

	public static @NotNull <T, Z> ItemStack addNBT(@NotNull ItemStack stack, @NotNull String key, @NotNull PersistentDataType<T,Z> type,Z value){
		return addNBT(stack,new NamespacedKey(plugin,key),type,value);
	}

	public static @NotNull <T, Z> ItemStack addNBT(@NotNull ItemStack stack, @NotNull NamespacedKey key, @NotNull PersistentDataType<T,Z> type, Z value){
		ItemStack target = stack.clone();
		ItemMeta meta = target.getItemMeta();
		meta.getPersistentDataContainer().set(key,type,value);
		target.setItemMeta(meta);
		return target;
	}

	public static @Nullable <T, Z> Z getNBT(@NotNull ItemStack stack, @NotNull String key, @NotNull PersistentDataType<T,Z> type){
		return getNBT(stack,new NamespacedKey(plugin,key),type);
	}

	public static @Nullable <T, Z> Z getNBT(@NotNull ItemStack stack, @NotNull NamespacedKey key, @NotNull PersistentDataType<T,Z> type){
		ItemStack target = stack.clone();
		PersistentDataContainer container = target.getItemMeta().getPersistentDataContainer();
		return container.has(key,type) ? container.get(key,type) : null;
	}

	public static <T, Z> boolean hasNBT(@NotNull ItemStack stack, @NotNull String key, @NotNull PersistentDataType<T,Z> type){
		return hasNBT(stack,new NamespacedKey(plugin,key),type);
	}

	public static <T, Z> boolean hasNBT(@NotNull ItemStack stack, @NotNull NamespacedKey key, @NotNull PersistentDataType<T,Z> type){
		ItemStack target = stack.clone();
		return target.getItemMeta().getPersistentDataContainer().has(key,type);
	}

	public static boolean hasNBT(@NotNull ItemStack stack, @NotNull String key){
		return hasNBT(stack,new NamespacedKey(plugin,key));
	}

	public static boolean hasNBT(@NotNull ItemStack stack, @NotNull NamespacedKey key){
		ItemStack target = stack.clone();
		return target.getItemMeta().getPersistentDataContainer().has(key);
	}

}
