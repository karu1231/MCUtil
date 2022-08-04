package karu1231.mcutil.message;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Message {

	//標準メッセージのheader色
	private static final TextColor color = NamedTextColor.GREEN;
	//エラーメッセージ色
	private static final TextColor error_color = NamedTextColor.RED;
	//メッセージの最初に置かれるheader(Ex.  [TEST] HOGEHOGE  )
	private static final String header = "[***] ";

	//AudienceはMessage sendableなinterface
	public static void send(Audience target, String str) {

		send(target, Component.text(str));


	}

	public static void send(Audience target, TextComponent comp) {
		target.sendMessage(Component.text().append(Component.text(header,color)).append(comp));
	}

	public static void allsend(String str) {

		for(Player p: Bukkit.getOnlinePlayers())
			send(p,str);
	}

	public static void allsend(TextComponent comp) {

		for(Player p: Bukkit.getOnlinePlayers())
			send(p,comp);
	}

	public static void Error(Audience target,ErrorMessage error) {

		target.sendMessage(Component.text(header,error_color).append(Component.text(error.text())));
	}
}
