<script>
	import '../app.css';
	import { page } from '$app/stores';
	import { onMount } from 'svelte';
	import { goto } from '$app/navigation';
	import { AppShell, AppBar, Drawer, getDrawerStore } from '@skeletonlabs/skeleton';
	import { initializeStores } from '@skeletonlabs/skeleton';

	// Props for children (Svelte 5 style)
	let { children } = $props();

	initializeStores();
	const drawerStore = getDrawerStore();

	let loading = $state(false);

	const navigationItems = [
		{ title: 'Dashboard', route: '/dashboard', icon: 'fa-solid fa-tachometer-alt' },
		{ title: 'Vacancies List', route: '/vacancies', icon: 'fa-solid fa-briefcase' },
		{ title: 'Add Vacancy', route: '/vacancy/add', icon: 'fa-solid fa-plus-circle' },
		{ title: 'Candidate Dashboard', route: '/candidates', icon: 'fa-solid fa-users' },
		{ title: 'Candidate List', route: '/candidates/list', icon: 'fa-solid fa-list' },
		{ title: 'Add Candidate', route: '/candidate/add', icon: 'fa-solid fa-user-plus' },
		{ title: 'Upload CV', route: '/cv/upload', icon: 'fa-solid fa-upload' }
	];

	function navigateTo(route) {
		drawerStore.close();
		goto(route);
	}

	function toggleDrawer() {
		drawerStore.open();
	}

	onMount(() => {
		// Initialize any global app state here
	});
</script>

<Drawer>
	<div class="p-4">
		<div class="mb-6">
			<h2 class="h3 font-bold">Recrutory</h2>
			<p class="text-sm opacity-75">Recruitment Management</p>
		</div>
		<nav class="space-y-1">
			{#each navigationItems as item}
				<button
					class="nav-item w-full text-left {$page.url.pathname === item.route ? 'active' : ''}"
					onclick={() => navigateTo(item.route)}
				>
					<i class="{item.icon} text-lg"></i>
					<span>{item.title}</span>
				</button>
			{/each}
		</nav>
	</div>
</Drawer>

<AppShell>
	<svelte:fragment slot="header">
		<AppBar>
			<svelte:fragment slot="lead">
				<button class="btn btn-sm mr-4" onclick={toggleDrawer}>
					<i class="fa-solid fa-bars text-lg"></i>
				</button>
				<strong class="text-xl uppercase">Recrutory</strong>
			</svelte:fragment>
			<svelte:fragment slot="trail">
				<div class="flex items-center gap-4">
					<span class="text-sm opacity-75">Recruitment System</span>
				</div>
			</svelte:fragment>
		</AppBar>
	</svelte:fragment>

	{#if loading}
		<div class="progress-indeterminate"></div>
	{/if}

	<main class="p-6">
		{@render children()}
	</main>
</AppShell>

<style>
	.nav-item {
		@apply flex items-center gap-3 px-4 py-2 rounded-lg hover:bg-surface-100-800-token transition-colors;
	}

	.nav-item.active {
		@apply bg-primary-500 text-white;
	}
</style>
