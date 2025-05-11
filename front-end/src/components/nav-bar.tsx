import { NavigationMenu, NavigationMenuContent, NavigationMenuItem, NavigationMenuLink, NavigationMenuList, NavigationMenuTrigger } from "@/components/ui/navigation-menu";
import { cn } from "@/lib/utils";
import { getNavList, getClubList } from "@/data/nav/nav-list";

type NavProps = {
  authenticated: boolean;
  username?: string;
  clubNames: string[];
};

const Navbar: React.FC<NavProps> = ({ authenticated, username, clubNames }) => {
  const clubList = getClubList(clubNames, authenticated);
  const navGroups = getNavList(authenticated, clubList, username);

  return (
    <NavigationMenu>
      <NavigationMenuList className="gap-4 text-lg font-semibold">
        {navGroups.map((group) => {
          const visibleItems = group.items.filter((item) => item.visible);
          if (visibleItems.length === 0) return null;

          return (
            <NavigationMenuItem key={`group-${group.id}`}>
              {group.groupName ? (
                <>
                  <NavigationMenuTrigger className="text-lg px-4 py-2">
                    {group.groupName.toUpperCase()}
                  </NavigationMenuTrigger>
                  <NavigationMenuContent>
                    <ul className="grid gap-2 p-4 w-[240px] text-base">
                      {visibleItems.map((item) => (
                        <li key={`item-${group.id}-${item.name}`}>
                          <NavigationMenuLink asChild>
                            <a
                              href={item.url}
                              className={cn(
                                "block px-3 py-2 hover:bg-gray-100 rounded transition",
                                item.className
                              )}
                            >
                              {item.name}
                            </a>
                          </NavigationMenuLink>
                        </li>
                      ))}
                    </ul>
                  </NavigationMenuContent>
                </>
              ) : (
                visibleItems.map((item) => (
                  <NavigationMenuLink asChild key={`item-${group.id}-${item.name}`}>
                    <a
                      href={item.url}
                      className={cn(
                        "px-4 py-2 text-lg hover:underline transition",
                        item.className
                      )}
                    >
                      {item.name}
                    </a>
                  </NavigationMenuLink>
                ))
              )}
            </NavigationMenuItem>
          );
        })}
      </NavigationMenuList>
    </NavigationMenu>
  );
};

export default Navbar;
