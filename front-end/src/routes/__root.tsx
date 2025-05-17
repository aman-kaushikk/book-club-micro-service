import { Outlet, createRootRoute } from '@tanstack/react-router'
import { TanStackRouterDevtools } from '@tanstack/react-router-devtools'
import { SidebarProvider, SidebarTrigger } from '@/components/ui/sidebar'
import { AppSidebar } from '@/components/app-sidebar'



export const Route = createRootRoute({
  component: () => (
    <>
        <SidebarProvider>
          <AppSidebar
            authenticated={true}
            clubNames={['Reader Club']}
            username="vivek"
          />
          <main className='flex-1 relative bg-'>
            <SidebarTrigger size="lg"/>
            <Outlet/>
          </main>
        </SidebarProvider>
      <TanStackRouterDevtools position="top-right" />
    </>
  ),
})
