# Lazy Loading of Folders

Final stage, we want to now bring 'expandable' back in the system.  We'll use an interface for this since I don't want to muddy my base class too much with this.  Files that are expandable are really just 'symlinks'/'directories' and maybe one or two minor other things.  Other files like devices and every other file aren't.

You could define it in base class here though.  I like the fact that we can use instanceof on the interface though as a boolean.
