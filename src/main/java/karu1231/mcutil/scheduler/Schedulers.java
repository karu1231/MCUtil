package karu1231.mcutil.scheduler;

import karu1231.mcutil.MCUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Schedulers {

	private static final BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
	private static final Plugin plugin = MCUtil.plugin;

	/* タスクが作られたすぐ次のtickで起動。 同期に使う。 */
	public static int runTask() {

		return scheduler.runTask(plugin, new Runnable() {

			@Override
			public void run() {

				/* 実行するコード */

			}

		}).getTaskId();

	}

	/* タスクが作られてから一定時間置かれたtickで起動。 */
	public static int delayTask() {

		/* 遅らせる時間を引数としてもたせる。 設定数値の単位はtickで1秒 = 20tick */
		long delaytick = 20L;

		return scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {

			@Override
			public void run() {

				/* 実行するコード */

			}

		}, delaytick);

	}

	/* タスクが作られてから止められるまで一生設定した時間ごとに実行される。 */
	public static int repeatTask() {

		/* リピートごとの遅延時間とタスクが作られてから一番最初のリピートの遅延時間をを引数としてもたせる。
		 * 最初のリピートの遅延時間を0にすると、タスクが作られた次のtickで実行される。
		 * 設定数値の単位はtickで1秒 = 20tick */
		long firstdelay = 0L;
		long repeattick = 10L;

		return scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {

			@Override
			public void run() {

				/* 実行されたコード */

			}

		}, firstdelay, repeattick);

	}
}
