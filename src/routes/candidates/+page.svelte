<script>
	import { onMount } from 'svelte';
	import { candidateAPI } from '$lib/services/api';
	import { goto } from '$app/navigation';
	import { ProgressRadial } from '@skeletonlabs/skeleton';

	let candidates = [];
	let filteredCandidates = [];
	let searchQuery = '';
	let loading = true;
	let error = null;

	onMount(async () => {
		await loadCandidates();
	});

	async function loadCandidates() {
		try {
			loading = true;
			candidates = await candidateAPI.getAll();
			filteredCandidates = candidates;
		} catch (err) {
			error = err.message;
			console.error('Failed to load candidates:', err);
		} finally {
			loading = false;
		}
	}

	function handleSearch() {
		if (!searchQuery.trim()) {
			filteredCandidates = candidates;
			return;
		}

		const query = searchQuery.toLowerCase();
		filteredCandidates = candidates.filter(candidate =>
			candidate.name?.toLowerCase().includes(query) ||
			candidate.email?.toLowerCase().includes(query) ||
			candidate.position?.toLowerCase().includes(query)
		);
	}

	function viewCandidate(candidate) {
		goto(`/candidate/${candidate.id}`);
	}

	$: {
		handleSearch();
	}
</script>

<svelte:head>
	<title>Candidates - Recrutory</title>
</svelte:head>

<div class="container h-full mx-auto flex justify-center items-start">
	<div class="space-y-10 text-center flex flex-col items-center w-full max-w-6xl">
		<!-- Header Section -->
		<div class="space-y-4 w-full">
			<div class="flex flex-col md:flex-row justify-between items-center gap-4">
				<h1 class="h1 gradient-heading">Candidate Dashboard</h1>
				<div class="flex gap-2">
					<button
						class="btn variant-soft-primary"
						on:click={() => goto('/candidate/add')}
					>
						<i class="fa-solid fa-user-plus mr-2"></i>
						Add Candidate
					</button>
					<button
						class="btn variant-filled-primary"
						on:click={() => goto('/cv/upload')}
					>
						<i class="fa-solid fa-upload mr-2"></i>
						Upload CV
					</button>
				</div>
			</div>

			<!-- Search Section -->
			<div class="input-group input-group-divider grid-cols-[auto_1fr_auto] max-w-md mx-auto">
				<div class="input-group-shim">
					<i class="fa-solid fa-search text-sm"></i>
				</div>
				<input
					class="input"
					type="search"
					placeholder="Search candidates..."
					bind:value={searchQuery}
				/>
			</div>
		</div>

		<!-- Content Section -->
		{#if loading}
			<div class="flex flex-col items-center justify-center py-20">
				<ProgressRadial value={undefined} stroke={100} meter="stroke-primary-500" track="stroke-primary-500/30" strokeLinecap="round" width="w-16" />
				<p class="text-lg mt-4">Loading candidates...</p>
			</div>
		{:else if error}
			<div class="card variant-soft-error p-6 max-w-md mx-auto">
				<div class="flex items-center gap-3">
					<i class="fa-solid fa-exclamation-triangle text-2xl"></i>
					<div>
						<h3 class="h3">Error Loading Candidates</h3>
						<p class="text-sm opacity-75">{error}</p>
					</div>
				</div>
				<hr class="my-4">
				<button class="btn variant-filled-error w-full" on:click={loadCandidates}>
					<i class="fa-solid fa-refresh mr-2"></i>
					Retry
				</button>
			</div>
		{:else if filteredCandidates.length === 0}
			<div class="placeholder py-20">
				<div class="placeholder-circle">
					<i class="fa-solid fa-users text-6xl"></i>
				</div>
				<div class="placeholder-content">
					<h3 class="h3">
						{searchQuery ? 'No candidates found' : 'No candidates available'}
					</h3>
					<p>
						{searchQuery
							? 'Try adjusting your search criteria or add new candidates.'
							: 'Get started by adding your first candidate to the system.'}
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
							on:click={() => goto('/candidate/add')}
						>
							<i class="fa-solid fa-user-plus mr-2"></i>
							Add First Candidate
						</button>
					</div>
				</div>
			</div>
		{:else}
			<!-- Candidates Grid -->
			<div class="candidate-grid">
				{#each filteredCandidates as candidate (candidate.id)}
					<div class="candidate-card">
						<div class="card-header">
							<div class="avatar-section">
								<div class="avatar bg-gradient-to-br variant-gradient-primary-secondary">
									{candidate.name?.charAt(0) || 'C'}
								</div>
								<div class="candidate-info">
									<h4 class="h4 font-bold">{candidate.name || 'Unknown Candidate'}</h4>
									<p class="text-sm opacity-75">{candidate.position || 'No position specified'}</p>
									{#if candidate.company}
										<p class="text-xs opacity-60">@ {candidate.company}</p>
									{/if}
								</div>
							</div>
							<button
								class="btn-icon btn-icon-sm variant-soft-primary"
								on:click={() => viewCandidate(candidate)}
								title="View Details"
							>
								<i class="fa-solid fa-eye"></i>
							</button>
						</div>

						<hr class="opacity-50">

						<div class="contact-info">
							{#if candidate.email}
								<div class="contact-item">
									<i class="fa-solid fa-envelope"></i>
									<span>{candidate.email}</span>
								</div>
							{/if}
							{#if candidate.phone}
								<div class="contact-item">
									<i class="fa-solid fa-phone"></i>
									<span>{candidate.phone}</span>
								</div>
							{/if}
							{#if candidate.location}
								<div class="contact-item">
									<i class="fa-solid fa-location-dot"></i>
									<span>{candidate.location}</span>
								</div>
							{/if}
						</div>

						{#if candidate.skills && candidate.skills.length > 0}
							<div class="skills-section">
								<div class="flex flex-wrap gap-1">
									{#each candidate.skills.slice(0, 4) as skill}
										<span class="chip variant-soft-primary">{skill}</span>
									{/each}
									{#if candidate.skills.length > 4}
										<span class="chip variant-ghost-surface">+{candidate.skills.length - 4}</span>
									{/if}
								</div>
							</div>
						{/if}

						<hr class="opacity-50">

						<div class="card-footer">
							<div class="status-info">
								<span class="text-xs opacity-75">
									Added: {candidate.createdDate ? new Date(candidate.createdDate).toLocaleDateString() : 'Unknown'}
								</span>
								<span class="badge variant-soft-{candidate.status === 'active' ? 'success' : candidate.status === 'interviewing' ? 'warning' : 'surface'}">
									{candidate.status || 'New'}
								</span>
							</div>
						</div>
					</div>
				{/each}
			</div>
		{/if}
	</div>
</div>

<style lang="scss">
	.gradient-heading {
		@apply bg-gradient-to-br from-blue-500 to-cyan-300 bg-clip-text text-transparent box-decoration-clone;
	}

	.candidate-grid {
		display: grid;
		grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
		gap: 1.5rem;
		width: 100%;
		padding: 0 1rem;
	}

	.candidate-card {
		@apply card card-hover p-4 space-y-4 bg-surface-100-800-token;
		transition: all 0.2s ease-in-out;
		cursor: pointer;

		&:hover {
			transform: translateY(-2px);
			@apply shadow-xl;
		}
	}

	.card-header {
		display: flex;
		justify-content: space-between;
		align-items: flex-start;
		gap: 1rem;
	}

	.avatar-section {
		display: flex;
		gap: 0.75rem;
		align-items: center;
		flex: 1;
	}

	.avatar {
		@apply w-12 h-12 rounded-full flex items-center justify-center text-white font-bold text-lg;
	}

	.candidate-info {
		flex: 1;
		min-width: 0; // Prevents text overflow

		h4 {
			@apply truncate;
		}

		p {
			@apply truncate;
		}
	}

	.contact-info {
		@apply space-y-2;
	}

	.contact-item {
		@apply flex items-center gap-2 text-sm;

		i {
			@apply w-4 text-center opacity-75;
		}

		span {
			@apply truncate flex-1;
		}
	}

	.skills-section {
		@apply min-h-[2rem] flex items-center;
	}

	.card-footer {
		@apply flex justify-between items-center;
	}

	.status-info {
		@apply flex justify-between items-center w-full text-xs;
	}

	// Mobile responsiveness
	@media (max-width: 768px) {
		.candidate-grid {
			grid-template-columns: 1fr;
			padding: 0 0.5rem;
		}

		.card-header {
			flex-direction: column;
			align-items: stretch;
			gap: 0.75rem;
		}

		.avatar-section {
			justify-content: space-between;
		}
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
</style>
