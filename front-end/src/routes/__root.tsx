import Navbar from '@/components/nav-bar'
import { Outlet, createRootRoute } from '@tanstack/react-router'
import { TanStackRouterDevtools } from '@tanstack/react-router-devtools'
import navlogo from "@/assets/nav-logo.svg"
export const Route = createRootRoute({
  component: () => (
    <>
      <Header />
      <Outlet />
      <footer>footer</footer>
      <TanStackRouterDevtools />
    </>
  ),
})

const Header = () => {
  return (
    <header>
      <img src={navlogo} alt='Naviation logo'></img>
      <Navbar authenticated={true} clubNames={["reader club"]}/>
    </header>
  )
}
