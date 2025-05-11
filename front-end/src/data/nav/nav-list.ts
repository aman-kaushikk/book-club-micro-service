type NavItem = {
  key: string;
  name: string;
  url: string;
  visible: boolean;
  className?: string;
};

type NavGroup = {
  key: string;
  id: number;
  group: string | null;
  groupName: string | null;
  items: NavItem[];
};

function getNavList(
  authenticated: boolean,
  clubList: NavItem[],
  username?: string
): NavGroup[] {
  return [
    {
      key: "group-1",
      id: 1,
      group: null,
      groupName: null,
      items: [
        {
          key: "join-book-club",
          name: "Join a book club",
          url: "/join-a-book-club",
          visible: true,
          className: "text-blue-600 hover:underline",
        },
      ],
    },
    {
      key: "group-2",
      id: 2,
      group: "Book",
      groupName: "Find a book",
      items: [
        {
          key: "search-books",
          name: "Search Books",
          url: "/books",
          visible: true,
          className: "text-blue-600 hover:underline",
        },
        {
          key: "top-books",
          name: "Top Books",
          url: "/best-book-club-books/this-month",
          visible: true,
          className: "text-blue-600 hover:underline",
        },
        {
          key: "indie-reads",
          name: "Great Indie Reads",
          url: "/great-indie-books",
          visible: true,
          className: "text-blue-600 hover:underline",
        },
      ],
    },
    {
      key: "group-3",
      id: 3,
      group: null,
      groupName: null,
      items: [
        {
          key: "discussion-guides",
          name: "Discussion Guides",
          url: "/discussion-guides",
          visible: true,
          className: "text-blue-600 hover:underline",
        },
      ],
    },
    {
      key: "group-4",
      id: 4,
      group: "Club",
      groupName: username ?? "My Clubs",
      items: [
        {
          key: "view-clubs",
          name: "View All Club",
          url: "/clubs",
          visible: authenticated,
          className: "text-green-600 hover:underline",
        },
        ...clubList,
        {
          key: "create-club",
          name: "Create New Club",
          url: "/create-club",
          visible: authenticated,
          className: "text-green-600 hover:underline",
        },
        {
          key: "find-club",
          name: "Find a club to join",
          url: "/join-a-book-club",
          visible: authenticated,
          className: "text-green-600 hover:underline",
        },
      ],
    },
    {
      key: "group-5",
      id: 5,
      group: null,
      groupName: null,
      items: [
        {
          key: "sign-up",
          name: "Sign up",
          url: "/sign-up",
          visible: !authenticated,
          className: "text-purple-600 hover:underline",
        },
      ],
    },
    {
      key: "group-6",
      id: 6,
      group: null,
      groupName: null,
      items: [
        {
          key: "sign-up",
          name: "Sign up",
          url: "/sign-up",
          visible: !authenticated,
          className: "text-purple-600 hover:underline",
        },
      ],
    },
  ];
}

function getClubList(clubnames: string[], authenticated: boolean): NavItem[] {
  return clubnames.map((clubname, index) => ({
    key: `club-${index}-${clubname}`,
    name: clubname,
    url: `/${clubname}/members`,
    visible: authenticated,
    className: "text-orange-600 hover:underline",
  }));
}

export { getNavList, getClubList };
