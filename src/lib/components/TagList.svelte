<script>
	let { tags = [] } = $props();
</script>

<div class="tag-list">
	{#if tags && tags.length > 0}
		<div class="tags">
			{#each tags as tag}
				<span class="tag">{tag.keyword || tag}</span>
			{/each}
		</div>
	{:else}
		<div class="no-tags">
			<span>No tags available</span>
		</div>
	{/if}
</div>

<style lang="scss">
	.tag-list {
		.tags {
			display: flex;
			flex-wrap: wrap;
			gap: 6px;
		}

		.tag {
			background: #e3f2fd;
			color: #1976d2;
			padding: 4px 8px;
			border-radius: 12px;
			font-size: 0.75em;
			font-weight: 500;
			border: 1px solid #bbdefb;
			transition: all 0.2s ease;

			&:hover {
				background: #bbdefb;
				transform: scale(1.05);
			}
		}

		.no-tags {
			color: #999;
			font-style: italic;
			font-size: 0.85em;
		}
	}
</style>
