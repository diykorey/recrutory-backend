<script>
	import { onMount } from 'svelte';
	import { vacancyAPI } from '$lib/services/api';
	import { goto } from '$app/navigation';
	import { ProgressRadial } from '@skeletonlabs/skeleton';

	let vacancies = [];
	let filteredVacancies = [];
	let searchQuery = '';
	let loading = true;
	let error = null;

	onMount(async () => {
		await loadVacancies();
	});

	async function loadVacancies() {
		try {
			loading = true;
			vacancies = await vacancyAPI.getAll();
			filteredVacancies = vacancies;
		} catch (err) {
			error = err.message;
			console.error('Failed to load vacancies:', err);
		} finally {
			loading = false;
		}
	}

	function handleSearch() {
		if (!searchQuery.trim()) {
			filteredVacancies = vacancies;
			return;
		}

		const query = searchQuery.toLowerCase();
		filteredVacancies = vacancies.filter(vacancy =>
			vacancy.name?.toLowerCase().includes(query) ||
			vacancy.requirements?.toLowerCase().includes(query) ||
			vacancy.location?.toLowerCase().includes(query)
		);
	}

	function editVacancy(vacancy) {
		goto(`/vacancy/edit/${vacancy.id}`);
	}

	function viewDetails(vacancy) {
		goto(`/vacancy/${vacancy.id}`);
	}

	async function deleteVacancy(vacancy) {
		if (!confirm(`Are you sure you want to delete "${vacancy.name}"?`)) {
			return;
		}

		try {
			await vacancyAPI.delete(vacancy.id);
			await loadVacancies();
		} catch (err) {
			console.error('Failed to delete vacancy:', err);
			alert('Failed to delete vacancy. Please try again.');
		}
	}

	$: {
		handleSearch();
	}
</script>

<svelte:head>
	<title>Vacancy List - Recrutory</title>
</svelte:head>

<div class="container h-full mx-auto flex justify-center items-start">
	<div class="space-y-10 text-center flex flex-col items-center w-full max-w-7xl">
		<!-- Header Section -->
		<div class="space-y-4 w-full">
			<div class="flex flex-col md:flex-row justify-between items-center gap-4">
				<h1 class="h1 gradient-heading">Vacancy Management</h1>
				<button
					class="btn variant-filled-primary flex items-center gap-2"
					on:click={() => goto('/vacancy/add')}
				>
					<i class="fa-solid fa-plus"></i>
					Add New Vacancy
				</button>
			</div>

			<!-- Search Section -->
			<div class="input-group input-group-divider grid-cols-[auto_1fr_auto] max-w-md mx-auto">
				<div class="input-group-shim">
					<i class="fa-solid fa-search text-sm"></i>
				</div>
				<input
					class="input"
					type="search"
					placeholder="Search vacancies..."
					bind:value={searchQuery}
				/>
			</div>
		</div>

		<!-- Content Section -->
		{#if loading}
			<div class="flex flex-col items-center justify-center py-20">
				<ProgressRadial value={undefined} stroke={100} meter="stroke-primary-500" track="stroke-primary-500/30" strokeLinecap="round" width="w-16" />
				<p class="text-lg mt-4">Loading vacancies...</p>
			</div>
		{:else if error}
			<div class="card variant-soft-error p-6 max-w-md mx-auto">
				<div class="flex items-center gap-3">
					<i class="fa-solid fa-exclamation-triangle text-2xl"></i>
					<div>
						<h3 class="h3">Error Loading Vacancies</h3>
						<p class="text-sm opacity-75">{error}</p>
					</div>
				</div>
				<hr class="my-4">
				<button class="btn variant-filled-error w-full" on:click={loadVacancies}>
					<i class="fa-solid fa-refresh mr-2"></i>
					Retry
				</button>
			</div>
		{:else if filteredVacancies.length === 0}
			<div class="placeholder py-20">
				<div class="placeholder-circle">
					<i class="fa-solid fa-briefcase text-6xl"></i>
				</div>
				<div class="placeholder-content">
					<h3 class="h3">
						{searchQuery ? 'No vacancies found' : 'No vacancies available'}
					</h3>
					<p>
						{searchQuery
							? 'Try adjusting your search criteria or create new vacancies.'
							: 'Create your first vacancy to start managing your recruitment process.'}
					</p>
					<div class="flex gap-2 justify-center mt-6">
						{#if searchQuery}
							<button
								class="btn variant-soft-surface"
								on:click={() => searchQuery = ''}
							>
								Clear Search
							</button>
						{/if}
						<button
							class="btn variant-filled-primary"
							on:click={() => goto('/vacancy/add')}
						>
							<i class="fa-solid fa-plus mr-2"></i>
							Create First Vacancy
						</button>
					</div>
				</div>
			</div>
		{:else}
			<!-- Vacancies Table -->
			<div class="table-container w-full">
				<div class="table-wrapper">
					<table class="table table-hover table-compact">
						<thead>
							<tr class="bg-surface-200-700-token">
								<th class="table-cell-fit">ID</th>
								<th>Position</th>
								<th class="table-cell-fit">Location</th>
								<th class="table-cell-fit">Status</th>
								<th class="table-cell-fit">Candidates</th>
								<th class="table-cell-fit">Created</th>
								<th class="table-cell-fit">Actions</th>
							</tr>
						</thead>
						<tbody>
							{#each filteredVacancies as vacancy (vacancy.id)}
								<tr class="hover:bg-surface-100-800-token">
									<td class="table-cell-fit">
										<div class="flex items-center gap-2">
											<span class="font-mono text-sm">#{vacancy.id}</span>
											{#if vacancy.hot}
												<span class="badge variant-filled-warning text-xs">HOT</span>
											{/if}
										</div>
									</td>
									<td>
										<div class="space-y-1">
											<div class="font-bold text-lg">{vacancy.name}</div>
											{#if vacancy.requirements}
												<div class="text-sm opacity-75 line-clamp-2">
													{vacancy.requirements.substring(0, 120)}
													{vacancy.requirements.length > 120 ? '...' : ''}
												</div>
											{/if}
										</div>
									</td>
									<td class="table-cell-fit">
										<div class="flex items-center gap-1">
											<i class="fa-solid fa-location-dot text-xs opacity-75"></i>
											<span class="text-sm">{vacancy.location || 'Remote'}</span>
										</div>
									</td>
									<td class="table-cell-fit">
										<span class="badge variant-soft-{vacancy.active ? 'success' : 'warning'}">
											{vacancy.active ? 'Active' : 'Inactive'}
										</span>
									</td>
									<td class="table-cell-fit text-center">
										<span class="badge variant-ghost-primary">
											{vacancy.candidateCount || 0}
										</span>
									</td>
									<td class="table-cell-fit text-sm opacity-75">
										{vacancy.createdDate ? new Date(vacancy.createdDate).toLocaleDateString() : 'N/A'}
									</td>
									<td class="table-cell-fit">
										<div class="flex items-center gap-1">
											<button
												class="btn-icon btn-icon-sm variant-soft-primary"
												on:click={() => viewDetails(vacancy)}
												title="View Details"
											>
												<i class="fa-solid fa-eye text-sm"></i>
											</button>
											<button
												class="btn-icon btn-icon-sm variant-soft-secondary"
												on:click={() => editVacancy(vacancy)}
												title="Edit Vacancy"
											>
												<i class="fa-solid fa-edit text-sm"></i>
											</button>
											<button
												class="btn-icon btn-icon-sm variant-soft-error"
												on:click={() => deleteVacancy(vacancy)}
												title="Delete Vacancy"
											>
												<i class="fa-solid fa-trash text-sm"></i>
											</button>
										</div>
									</td>
								</tr>
							{/each}
						</tbody>
					</table>
				</div>
			</div>
		{/if}
	</div>
</div>

<style lang="scss">
	.gradient-heading {
		@apply bg-gradient-to-br from-blue-500 to-cyan-300 bg-clip-text text-transparent box-decoration-clone;
	}

	.table-container {
		@apply card overflow-hidden;
	}

	.table-wrapper {
		@apply overflow-x-auto;
	}

	.table {
		width: 100%;

		th {
			@apply font-semibold text-left px-4 py-3 text-sm;
			white-space: nowrap;
		}

		td {
			@apply px-4 py-3 text-sm;
			vertical-align: top;
		}

		.table-cell-fit {
			width: 1%;
			white-space: nowrap;
		}
	}

	.line-clamp-2 {
		display: -webkit-box;
		-webkit-line-clamp: 2;
		-webkit-box-orient: vertical;
		overflow: hidden;
	}

	// Placeholder styles
	.placeholder {
		@apply flex flex-col items-center text-center;
	}

	.placeholder-circle {
		@apply w-32 h-32 mx-auto bg-gradient-to-br variant-gradient-primary-secondary rounded-full flex items-center justify-center mb-6;
		color: white;
	}

	.placeholder-content {
		@apply space-y-4 max-w-md;

		h3 {
			@apply text-2xl font-bold;
		}

		p {
			@apply opacity-75;
		}
	}

	// Mobile responsiveness
	@media (max-width: 768px) {
		.table-wrapper {
			@apply overflow-x-scroll;
		}

		.table {
			min-width: 700px; // Ensure table doesn't get too cramped on mobile
		}
	}
</style>
