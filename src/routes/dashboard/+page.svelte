<script>
	import { onMount } from 'svelte';
	import { vacancyAPI } from '$lib/services/api';
	import VacancyCard from '$lib/components/VacancyCard.svelte';
	import VacancySidePanel from '$lib/components/VacancySidePanel.svelte';
	import { goto } from '$app/navigation';

	let vacancies = $state([]);
	let currentVacancy = $state(null);
	let filterValue = $state('');
	let loading = $state(true);
	let error = $state(null);

	onMount(async () => {
		await loadVacancies();
	});

	async function loadVacancies() {
		try {
			loading = true;
			vacancies = await vacancyAPI.getAll();
		} catch (err) {
			error = err.message;
			console.error('Failed to load vacancies:', err);
		} finally {
			loading = false;
		}
	}

	function selectVacancy(vacancy) {
		currentVacancy = vacancy;
	}

	function closeSidePanel() {
		currentVacancy = null;
	}

	// Derived state using $derived
	const filteredVacancies = $derived(
		vacancies.filter(vacancy =>
			vacancy.name?.toLowerCase().includes(filterValue.toLowerCase()) ||
			vacancy.description?.toLowerCase().includes(filterValue.toLowerCase())
		)
	);
</script>

<svelte:head>
	<title>Vacancy Dashboard - Recrutory</title>
</svelte:head>

<div class="container mx-auto p-6">
	<div class="flex justify-between items-center mb-8 flex-wrap gap-4">
		<h1 class="h1 text-primary-700">Vacancy Dashboard</h1>
		<div class="flex items-center gap-4">
			<label class="label">
				<input
					class="input w-80"
					type="search"
					bind:value={filterValue}
					placeholder="Search vacancies..."
				/>
			</label>
			<button class="btn variant-filled-primary" onclick={() => goto('/vacancy/add')}>
				<i class="material-icons mr-2">add</i>
				Add Vacancy
			</button>
		</div>
	</div>

	{#if loading}
		<div class="flex flex-col items-center justify-center py-20">
			<div class="placeholder-circle animate-pulse w-16 h-16 mb-4"></div>
			<p class="text-surface-600-300-token">Loading vacancies...</p>
		</div>
	{:else if error}
		<div class="card variant-filled-error p-6 text-center">
			<p class="text-white mb-4">Error: {error}</p>
			<button class="btn variant-filled-surface" onclick={loadVacancies}>Retry</button>
		</div>
	{:else}
		<div class="flex gap-6 {currentVacancy ? 'grid grid-cols-1 lg:grid-cols-2' : ''}">
			<div class="vacancy-grid {currentVacancy ? 'max-h-screen overflow-y-auto' : ''}">
				{#each filteredVacancies as vacancy (vacancy.id)}
					<VacancyCard
						{vacancy}
						bind:selected={currentVacancy?.id === vacancy.id}
						onselect={() => selectVacancy(vacancy)}
					/>
				{:else}
					<div class="col-span-full text-center py-20">
						<i class="material-icons text-6xl text-surface-400 mb-4">work_off</i>
						<h3 class="h3 mb-2">No vacancies found</h3>
						<p class="text-surface-600-300-token mb-6">
							{filterValue ? 'Try adjusting your search criteria.' : 'Create your first vacancy to get started.'}
						</p>
						<button class="btn variant-filled-primary" onclick={() => goto('/vacancy/add')}>
							Add Vacancy
						</button>
					</div>
				{/each}
			</div>

			{#if currentVacancy}
				<div class="card w-full lg:w-96 max-h-screen overflow-hidden">
					<VacancySidePanel
						vacancy={currentVacancy}
						onclose={closeSidePanel}
						onupdated={loadVacancies}
					/>
				</div>
			{/if}
		</div>
	{/if}
</div>
