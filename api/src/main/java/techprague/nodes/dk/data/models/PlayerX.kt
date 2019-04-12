package techprague.nodes.dk.data.models

data class PlayerX(
    val abandons: Int,
    val ability_targets: Map<String, Int>,
    val ability_upgrades_arr: List<Int>,
    val ability_uses: Map<String, Int>,
    val account_id: Int,
    val actions: Map<Int, Int>,
    val actions_per_min: Int,
    val additional_units: Any?,
    val ancient_kills: Int,
    val assists: Int,
    val backpack_0: Int,
    val backpack_1: Int,
    val backpack_2: Int,
    val benchmarks: Benchmarks,
    val buyback_count: Int,
    val buyback_log: List<BuybackLog>,
    val camps_stacked: Int,
    val cluster: Int,
    val connection_log: List<Any>,
    val courier_kills: Int,
    val creeps_stacked: Int,
    val damage: Map<String, Int>,
    val damage_taken: DamageTaken,
    val damage_targets: Map<String, Int>,
    val deaths: Int,
    val denies: Int,
    val dn_t: List<Int>,
    val duration: Int,
    val first_purchase_time: FirstPurchaseTime,
    val firstblood_claimed: Int,
    val game_mode: Int,
    val gold: Int,
    val gold_per_min: Int,
    val gold_reasons: Map<Int, Int>,
    val gold_spent: Int,
    val gold_t: List<Int>,
    val hero_damage: Int,
    val hero_healing: Int,
    val hero_hits: HeroHits,
    val hero_id: Int,
    val hero_kills: Int,
    val isRadiant: Boolean,
    val is_contributor: Boolean,
    val is_roaming: Boolean,
    val item_0: Int,
    val item_1: Int,
    val item_2: Int,
    val item_3: Int,
    val item_4: Int,
    val item_5: Int,
    val item_usage: ItemUsage,
    val item_uses: ItemUsesX,
    val item_win: ItemWin,
    val kda: Int,
    val kill_streaks: KillStreaks,
    val killed: KilledX,
    val killed_by: KilledBy,
    val kills: Int,
    val kills_log: List<KillsLog>,
    val kills_per_min: Double,
    val lane: Int,
    val lane_efficiency: Double,
    val lane_efficiency_pct: Int,
    val lane_kills: Int,
    val lane_role: Int,
    val last_hits: Int,
    val last_login: String,
    val leaver_status: Int,
    val level: Int,
    val lh_t: List<Int>,
    val life_state: Map<Int, Int>,
    val life_state_dead: Int,
    val lobby_type: Int,
    val lose: Int,
    val match_id: Long,
    val max_hero_hit: MaxHeroHit,
    val multi_kills: Map<Int, Int>,
    val name: Any?,
    val necronomicon_kills: Int,
    val neutral_kills: Int,
    val obs: Obs,
    val obs_left_log: List<Any>,
    val obs_log: List<Any>,
    val obs_placed: Int,
    val observer_kills: Int,
    val observer_uses: Int,
    val party_id: Int,
    val party_size: Int,
    val patch: Int,
    val performance_others: Any?,
    val permanent_buffs: Any?,
    val personaname: String,
    val pings: Int,
    val player_slot: Int,
    val pred_vict: Boolean,
    val purchase: Purchase,
    val purchase_log: List<PurchaseLog>,
    val purchase_time: PurchaseTime,
    val purchase_tpscroll: Int,
    val purchase_ward_observer: Int,
    val purchase_ward_sentry: Int,
    val radiant_win: Boolean,
    val randomed: Boolean,
    val rank_tier: Any?,
    val region: Int,
    val repicked: Any?,
    val roshan_kills: Int,
    val roshans_killed: Int,
    val rune_pickups: Int,
    val runes: Map<Int, Int>,
    val runes_log: List<RunesLog>,
    val sen: Sen,
    val sen_left_log: List<Any>,
    val sen_log: List<Any>,
    val sen_placed: Int,
    val sentry_kills: Int,
    val sentry_uses: Int,
    val start_time: Int,
    val stuns: Double,
    val teamfight_participation: Double,
    val times: List<Int>,
    val total_gold: Int,
    val total_xp: Int,
    val tower_damage: Int,
    val tower_kills: Int,
    val towers_killed: Int,
    val win: Int,
    val xp_per_min: Int,
    val xp_reasons: Map<Int, Int>,
    val xp_t: List<Int>
)