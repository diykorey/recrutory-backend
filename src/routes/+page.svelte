<script>
	import { goto } from '$app/navigation';
</script>

<svelte:head>
	<title>Recrutory - Recruitment Management System</title>
</svelte:head>

<div class="container mx-auto p-6">
	<div class="text-center py-20">
		<div class="space-y-8">
			<div class="space-y-4">
				<h1 class="h1 text-primary-700">Welcome to Recrutory</h1>
				<p class="text-xl text-surface-600-300-token">Your comprehensive recruitment management solution</p>
			</div>

			<div class="flex justify-center gap-4 flex-wrap">
				<button class="btn variant-filled-primary lg" onclick={() => goto('/dashboard')}>
					<i class="material-icons mr-2">dashboard</i>
					Go to Dashboard
				</button>
				<button class="btn variant-outline-primary lg" onclick={() => goto('/vacancy/add')}>
					<i class="material-icons mr-2">add_circle</i>
					Add New Vacancy
				</button>
			</div>
		</div>
	</div>

	<div class="grid grid-cols-1 md:grid-cols-3 gap-6 mt-16">
		<div class="card p-6 text-center space-y-4">
			<div class="flex justify-center">
				<i class="material-icons text-4xl text-primary-500">work</i>
			</div>
			<h3 class="h3">Vacancy Management</h3>
			<p class="text-surface-600-300-token">Create, manage, and track job vacancies with ease</p>
			<button class="btn variant-filled-primary" onclick={() => goto('/vacancies')}>
				View Vacancies
			</button>
		</div>

		<div class="card p-6 text-center space-y-4">
			<div class="flex justify-center">
				<i class="material-icons text-4xl text-secondary-500">people</i>
			</div>
			<h3 class="h3">Candidate Tracking</h3>
			<p class="text-surface-600-300-token">Manage candidates throughout the recruitment process</p>
			<button class="btn variant-filled-secondary" onclick={() => goto('/candidates')}>
				View Candidates
			</button>
		</div>

		<div class="card p-6 text-center space-y-4">
			<div class="flex justify-center">
				<i class="material-icons text-4xl text-tertiary-500">upload_file</i>
			</div>
			<h3 class="h3">CV Management</h3>
			<p class="text-surface-600-300-token">Upload and organize candidate CVs and documents</p>
			<button class="btn variant-filled-tertiary" onclick={() => goto('/cv/upload')}>
				Upload CV
			</button>
		</div>
	</div>
</div>
