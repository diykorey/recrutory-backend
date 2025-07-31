<script>
	import { vacancyAPI } from '$lib/services/api';
	import { TabGroup, Tab } from '@skeletonlabs/skeleton';
	import TagList from './TagList.svelte';

	let { vacancy } = $props();

	let activeTab = $state(0);
	let editingVacancy = $state({ ...vacancy });
	let newTag = $state('');
	let updating = $state(false);

	const tabs = [
		{ label: 'Main', component: 'main' },
		{ label: 'Flow', component: 'flow' },
		{ label: 'Suggestions', component: 'suggestions' },
		{ label: 'Notes', component: 'notes' }
	];

	function closePanel() {
		// Dispatch custom event for parent component
		const event = new CustomEvent('close');
		document.dispatchEvent(event);
	}

	async function updateVacancyInfo() {
		if (updating) return;

		try {
			updating = true;
			await vacancyAPI.update(vacancy.id, editingVacancy);
			// Dispatch updated event
			const event = new CustomEvent('updated');
			document.dispatchEvent(event);
		} catch (error) {
			console.error('Failed to update vacancy:', error);
		} finally {
			updating = false;
		}
	}

	function addTag() {
		if (!newTag.trim()) return;

		const tag = { keyword: newTag.trim() };
		editingVacancy.tags = [...(editingVacancy.tags || []), tag];
		newTag = '';
		updateVacancyInfo();
	}

	function removeTag(tagToRemove) {
		editingVacancy.tags = editingVacancy.tags.filter(tag =>
			tag.keyword !== tagToRemove.keyword
		);
		updateVacancyInfo();
	}

	function handleKeyPress(event) {
		if (event.key === 'Enter' || event.key === ' ' || event.key === ',') {
			event.preventDefault();
			addTag();
		}
	}

	// Update editingVacancy when vacancy prop changes
	$effect(() => {
		if (vacancy) {
			editingVacancy = { ...vacancy };
		}
	});
</script>

<div class="card h-full flex flex-col">
	<header class="card-header bg-primary-500 text-white">
		<div class="flex justify-between items-center">
			<div class="flex items-center gap-3">
				<h2 class="h3 font-semibold">{vacancy.name}</h2>
				{#if vacancy.hot}
					<span class="badge variant-filled-warning">HOT</span>
				{/if}
			</div>
			<button class="btn-icon btn-icon-sm" onclick={closePanel}>
				<i class="material-icons">close</i>
			</button>
		</div>
	</header>

	{#if updating}
		<div class="progress-indeterminate"></div>
	{/if}

	<div class="flex-1 flex flex-col p-4">
		<TabGroup>
			{#each tabs as tab, i}
				<Tab bind:group={activeTab} name={tab.component} value={i}>
					{tab.label}
				</Tab>
			{/each}

			<svelte:fragment slot="panel">
				{#if activeTab === 0}
					<!-- Main Tab -->
					<div class="space-y-4 mt-4">
						<label class="label">
							<span>Name</span>
							<input
								class="input"
								type="text"
								bind:value={editingVacancy.name}
								onblur={updateVacancyInfo}
								placeholder="Vacancy name"
							/>
						</label>

						<label class="label">
							<span>Requirements</span>
							<textarea
								class="textarea"
								bind:value={editingVacancy.requirements}
								onblur={updateVacancyInfo}
								placeholder="Job requirements and description"
								rows="4"
							></textarea>
						</label>

						<label class="label">
							<span>Location</span>
							<input
								class="input"
								type="text"
								bind:value={editingVacancy.location}
								onblur={updateVacancyInfo}
								placeholder="Job location"
							/>
						</label>

						<label class="label">
							<span>Salary</span>
							<input
								class="input"
								type="text"
								bind:value={editingVacancy.salary}
								onblur={updateVacancyInfo}
								placeholder="Salary range"
							/>
						</label>

						<div class="space-y-3">
							<h5 class="h5 font-semibold">Tags</h5>
							<div class="tags-display">
								{#if editingVacancy.tags && editingVacancy.tags.length > 0}
									<div class="flex flex-wrap gap-2">
										{#each editingVacancy.tags as tag}
											<span class="chip variant-soft-primary">
												{tag.keyword}
												<button
													class="ml-2"
													onclick={() => removeTag(tag)}
													title="Remove tag"
												>
													<i class="material-icons text-xs">close</i>
												</button>
											</span>
										{/each}
									</div>
								{:else}
									<p class="text-surface-500 italic">No tags added yet</p>
								{/if}
							</div>

							<label class="label">
								<span>Add new tag (press Enter, Space, or comma to add)</span>
								<input
									class="input"
									type="text"
									bind:value={newTag}
									onkeydown={handleKeyPress}
									placeholder="Type tag name..."
								/>
							</label>
						</div>
					</div>

				{:else if activeTab === 1}
					<!-- Flow Tab -->
					<div class="mt-4">
						<h3 class="h3 mb-4">Vacancy Flow</h3>
						<p class="text-surface-600-300-token">Flow management features coming soon...</p>
					</div>

				{:else if activeTab === 2}
					<!-- Suggestions Tab -->
					<div class="mt-4">
						<h3 class="h3 mb-4">Candidate Suggestions</h3>
						<p class="text-surface-600-300-token">Candidate suggestions based on vacancy requirements...</p>
					</div>

				{:else if activeTab === 3}
					<!-- Notes Tab -->
					<div class="mt-4">
						<label class="label">
							<span>Notes</span>
							<textarea
								class="textarea"
								bind:value={editingVacancy.notes}
								onblur={updateVacancyInfo}
								placeholder="Add notes about this vacancy"
								rows="6"
							></textarea>
						</label>
					</div>
				{/if}
			</svelte:fragment>
		</TabGroup>
	</div>
</div>

<style lang="scss">
	.card {
		&-header {
			padding: 16px;
			display: flex;
			justify-content: space-between;
			align-items: center;
			background-color: #2196f3;
			color: white;
			border-top-left-radius: 8px;
			border-top-right-radius: 8px;

			.badge {
				background-color: #ff5722;
				padding: 4px 8px;
				border-radius: 12px;
				font-size: 0.75em;
				font-weight: 500;
				text-transform: uppercase;
			}
		}

		.progress-indeterminate {
			height: 4px;
			background-color: #e0e0e0;
			animation: indeterminate 2s infinite linear;

			@keyframes indeterminate {
				0% { transform: translateX(-100%); }
				100% { transform: translateX(100%); }
			}
		}

		.flex-1 {
			display: flex;
			flex-direction: column;
			padding: 16px;

			textarea {
				resize: vertical;
			}
		}
	}

	.label {
		display: block;
		margin-bottom: 8px;
		font-weight: 500;
		color: #333;

		span {
			display: block;
			margin-bottom: 4px;
		}
	}

	.input, .textarea {
		width: 100%;
		padding: 12px;
		border: 1px solid #ccc;
		border-radius: 4px;
		font-size: 1em;
		color: #333;

		&:focus {
			border-color: #2196f3;
			outline: none;
		}
	}

	.textarea {
		resize: vertical;
	}

	.tags-display {
		margin-bottom: 16px;

		.chip {
			display: inline-flex;
			align-items: center;
			background-color: #e3f2fd;
			color: #1976d2;
			padding: 6px 12px;
			border-radius: 16px;
			font-size: 0.85em;
			border: 1px solid #bbdefb;

			button {
				background: none;
				border: none;
				margin-left: 8px;
				padding: 0;
				cursor: pointer;
				display: flex;
				align-items: center;
				color: #1976d2;

				i {
					font-size: 16px;
				}

				&:hover {
					color: #d32f2f;
				}
			}
		}

		.text-surface-500 {
			color: #999;
			font-style: italic;
			margin: 0;
		}
	}

	.mt-4 {
		margin-top: 16px;
	}

	.mb-4 {
		margin-bottom: 16px;
	}

	.space-y-3 {
		> * + * {
			margin-top: 12px;
		}
	}

	@media (max-width: 768px) {
		.card-header {
			flex-direction: column;
			align-items: flex-start;

			.badge {
				margin-top: 4px;
			}
		}

		.flex-1 {
			padding: 12px;
		}
	}
</style>
