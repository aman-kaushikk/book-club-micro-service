export type MenuItem = {
  key: string;
  name: string;
  url: string;
  visible: boolean;
  className: string;
};

export type MenuGroup = {
  groupName: string | null;
  items: MenuItem[];
  key : string;
};

export type MenuData = MenuGroup[];
