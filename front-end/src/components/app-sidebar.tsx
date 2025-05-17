import {
  Collapsible,
  CollapsibleContent,
  CollapsibleTrigger,
} from '@/components/ui/collapsible'
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from '@/components/ui/dropdown-menu'
import {
  Sidebar,
  SidebarContent,
  SidebarFooter,
  SidebarGroup,
  SidebarGroupContent,
  SidebarGroupLabel,
  SidebarHeader,
  SidebarInset,
  SidebarMenu,
  SidebarMenuButton,
  SidebarMenuItem,
} from '@/components/ui/sidebar'
import { getClubList, getNavList } from '@/data/nav/nav-list'
import type { MenuGroup } from '@/data/types'
import { Link } from '@tanstack/react-router'
import { ChevronDown, ChevronUp, User2 } from 'lucide-react'

import navLogo from '@/assets/nav-logo.svg'

interface SidebarGroupRendererProps {
  group: MenuGroup
}

interface AppSidebarProps {
  authenticated: boolean
  username?: string
  clubNames: string[]
}

export const AppSidebar: React.FC<AppSidebarProps> = ({
  authenticated,
  username,
  clubNames,
}) => {
  const clubList = getClubList(clubNames, authenticated)
  const navGroups = getNavList(authenticated, clubList, username)
  return (
    <Sidebar variant="inset" collapsible="offcanvas" >
      <SidebarHeader>
        <img src={navLogo} alt="main logo" />
      </SidebarHeader>
      <SidebarInset>
        <SidebarContent>
          {navGroups.map((navGroup) => (
            <SidebarGroupRenderer group={navGroup} key={navGroup.key} />
          ))}
        </SidebarContent>
      </SidebarInset>
      <SideBarFooterRenderer />
    </Sidebar>
  )
}

const SideBarFooterRenderer = () => {
  return (
    <SidebarFooter>
      <SidebarMenu>
        <SidebarMenuItem>
          <DropdownMenu>
            <DropdownMenuTrigger asChild>
              <SidebarMenuButton>
                <User2 /> Username
                <ChevronUp className="ml-auto" />
              </SidebarMenuButton>
            </DropdownMenuTrigger>
            <DropdownMenuContent
              side="top"
              className="w-[--radix-popper-anchor-width]"
            >
              <DropdownMenuItem>
                <span>Account</span>
              </DropdownMenuItem>
              <DropdownMenuItem>
                <span>Billing</span>
              </DropdownMenuItem>
              <DropdownMenuItem>
                <span>Sign out</span>
              </DropdownMenuItem>
            </DropdownMenuContent>
          </DropdownMenu>
        </SidebarMenuItem>
      </SidebarMenu>
    </SidebarFooter>
  )
}

const SidebarGroupRenderer: React.FC<SidebarGroupRendererProps> = ({
  group,
}) => {
  const { groupName, items } = group

  return (
    <Collapsible defaultOpen className="group/collapsible">
      <SidebarGroup>
        {groupName && (
          <SidebarGroupLabel asChild>
            <CollapsibleTrigger>
              <span className="text-[1.1rem] text-gray-700 uppercase">
                {groupName}
              </span>
              <ChevronDown className="ml-auto transition-transform group-data-[state=open]/collapsible:rotate-180" />
            </CollapsibleTrigger>
          </SidebarGroupLabel>
        )}
        <CollapsibleContent>
          <SidebarGroupContent>
            <SidebarMenu>
              {items
                .filter((item) => item.visible)
                .map((item) => (
                  <SidebarMenuItem key={item.key}>
                    <SidebarMenuButton asChild>
                      <Link to={item.url} className={item.className}>
                        {item.name}
                      </Link>
                    </SidebarMenuButton>
                  </SidebarMenuItem>
                ))}
            </SidebarMenu>
          </SidebarGroupContent>
        </CollapsibleContent>
      </SidebarGroup>
    </Collapsible>
  )
}
