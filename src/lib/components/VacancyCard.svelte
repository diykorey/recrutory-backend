<script>
	import TagList from './TagList.svelte';

	let { vacancy, selected = $bindable(false) } = $props();

	function handleSelect() {
		selected = !selected;
		// Dispatch custom event for parent component
		const event = new CustomEvent('select', { detail: vacancy });
		document.dispatchEvent(event);
	}

	function handleQuickAction(action, event) {
		event.stopPropagation();
		// Dispatch custom event for parent component
		const customEvent = new CustomEvent('quickAction', { detail: { action, vacancy } });
		document.dispatchEvent(customEvent);
	}

	function truncateText(text, limit = 110) {
		if (!text) return '';
		return text.length > limit ? text.substring(0, limit) + '...' : text;
	}
</script>

<div class="vacancy-card {selected ? 'selected' : ''}" onclick={handleSelect} role="button" tabindex="0">
	{#if !vacancy.allowArchive}
		<div class="flex justify-between items-start mb-4">
			<div class="flex items-center gap-3">
				<span class="badge variant-soft-surface">#{vacancy.id}</span>
				{#if vacancy.hot}
					<span class="hot-badge">HOT</span>
				{/if}
			</div>

			<div class="quick-actions">
				<button
					class="btn-icon btn-icon-sm variant-filled-error"
					onclick={(e) => handleQuickAction('archive', e)}
					title="Archive"
				>
					A
				</button>
				<button
					class="btn-icon btn-icon-sm variant-filled-primary"
					onclick={(e) => handleQuickAction('notes', e)}
					title="Notes"
				>
					N
				</button>
				<button
					class="btn-icon btn-icon-sm variant-filled-secondary"
					onclick={(e) => handleQuickAction('flow', e)}
					title="Flow"
				>
					F
				</button>
				<button
					class="btn-icon btn-icon-sm variant-filled-tertiary"
					onclick={(e) => handleQuickAction('main', e)}
					title="Main"
				>
					M
				</button>
			</div>
		</div>

		<div class="mb-3">
			<h3 class="h4 font-semibold">{vacancy.name}</h3>
		</div>

		<div class="mb-3">
			<p class="text-sm text-surface-600-300-token font-medium mb-1">Description:</p>
			<p class="text-sm">{truncateText(vacancy.requirements, 110)}</p>
		</div>

		<div class="mb-4">
			<p class="text-sm text-surface-600-300-token font-medium mb-2">Tags:</p>
			<TagList tags={vacancy.tags} />
		</div>

		<div class="space-y-2 mb-4">
			<div class="flex justify-between text-xs">
				<span class="text-surface-600-300-token">Location:</span>
				<span>{vacancy.location || 'Not specified'}</span>
			</div>
			<div class="flex justify-between text-xs">
				<span class="text-surface-600-300-token">Type:</span>
				<span>{vacancy.type || 'Full-time'}</span>
			</div>
			{#if vacancy.salary}
				<div class="flex justify-between text-xs">
					<span class="text-surface-600-300-token">Salary:</span>
					<span>{vacancy.salary}</span>
				</div>
			{/if}
		</div>

		<div class="flex justify-between items-center pt-3 border-t border-surface-300-600-token">
			<div class="flex items-center gap-1 text-xs text-surface-600-300-token">
				<i class="material-icons text-sm">people</i>
				<span>{vacancy.candidatesCount || 0} candidates</span>
			</div>
			<div class="text-xs text-surface-600-300-token">
				{new Date(vacancy.createdAt || Date.now()).toLocaleDateString()}
			</div>
		</div>
	{:else}
		<div class="text-center py-6">
			<div class="flex items-center justify-center gap-2 mb-3">
				<i class="material-icons text-surface-500">archive</i>
				<span class="text-surface-600-300-token">Archived Vacancy</span>
			</div>
			<div class="font-medium mb-4">{vacancy.name}</div>
			<button
				class="btn variant-outline-primary btn-sm"
				onclick={(e) => handleQuickAction('restore', e)}
			>
				Restore
			</button>
		</div>
	{/if}
</div>

<style lang="scss">
	.vacancy-card {
		background: white;
		border-radius: 8px;
		box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
		padding: 20px;
		cursor: pointer;
		transition: all 0.2s ease;
		border: 2px solid transparent;

		&:hover {
			box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
			transform: translateY(-2px);
		}

		&.selected {
			border-color: #2196f3;
			box-shadow: 0 4px 12px rgba(33, 150, 243, 0.3);
		}

		&:focus {
			outline: none;
			border-color: #2196f3;
		}
	}

	.card-header {
		display: flex;
		justify-content: space-between;
		align-items: flex-start;
		margin-bottom: 16px;
	}

	.vacancy-info {
		display: flex;
		align-items: center;
		gap: 12px;

		.vacancy-id {
			background: #f5f5f5;
			padding: 4px 8px;
			border-radius: 4px;
			font-weight: 500;
			font-size: 0.9em;
		}

		.hot-badge {
			background: #ff5722;
			color: white;
			padding: 2px 8px;
			border-radius: 12px;
			font-size: 0.75em;
			font-weight: 500;
			text-transform: uppercase;
		}
	}

	.quick-actions {
		display: flex;
		gap: 8px;
		flex-wrap: wrap;
	}

	.vacancy-name {
		margin-bottom: 12px;

		h3 {
			margin: 0;
			color: #333;
			font-size: 1.2em;
			font-weight: 500;
		}
	}

	.vacancy-description, .vacancy-tags {
		margin-bottom: 12px;

		label {
			display: block;
			font-weight: 500;
			color: #666;
			margin-bottom: 4px;
			font-size: 0.9em;
		}

		p {
			margin: 0;
			color: #555;
			line-height: 1.4;
		}
	}

	.vacancy-details {
		margin-bottom: 16px;

		.detail-item {
			display: flex;
			justify-content: space-between;
			margin-bottom: 4px;
			font-size: 0.9em;

			.label {
				color: #666;
				font-weight: 500;
			}

			.value {
				color: #333;
			}
		}
	}

	.vacancy-footer {
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding-top: 12px;
		border-top: 1px solid #eee;
		font-size: 0.85em;
		color: #666;

		.candidates-count {
			display: flex;
			align-items: center;
			gap: 4px;

			i {
				font-size: 16px;
			}
		}
	}

	.archived-card {
		text-align: center;
		padding: 20px;
		background: #f5f5f5;
		border-radius: 8px;

		.archived-header {
			display: flex;
			align-items: center;
			justify-content: center;
			gap: 8px;
			margin-bottom: 12px;
			color: #666;

			i {
				color: #999;
			}
		}

		.archived-name {
			font-weight: 500;
			margin-bottom: 16px;
			color: #333;
		}
	}

	@media (max-width: 768px) {
		.quick-actions {
			flex-direction: column;
			gap: 4px;
		}

		.card-header {
			flex-direction: column;
			gap: 12px;
		}

		.vacancy-details .detail-item {
			flex-direction: column;
			gap: 2px;
		}
	}
</style>
