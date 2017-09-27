0x0040a071:	pusha
0x0040a072:	call 0x0040a077
0x0040a077:	popl %eax
0x0040a078:	addl %eax, $0x29f<UINT32>
0x0040a07d:	movl %esi, (%eax)
0x0040a07f:	addl %esi, %eax
0x0040a081:	subl %eax, %eax
0x0040a083:	movl %edi, %esi
0x0040a085:	lodsw %ax, %ds:(%esi)
0x0040a087:	shll %eax, $0xc<UINT8>
0x0040a08a:	movl %ecx, %eax
0x0040a08c:	pushl %eax
0x0040a08d:	lodsl %eax, %ds:(%esi)
0x0040a08e:	subl %ecx, %eax
0x0040a090:	addl %esi, %ecx
0x0040a092:	movl %ecx, %eax
0x0040a094:	pushl %edi
0x0040a095:	pushl %ecx
0x0040a096:	decl %ecx
0x0040a097:	movb %al, 0x6(%ecx,%edi)
0x0040a09b:	movb (%ecx,%esi), %al
0x0040a09e:	jne 0x0040a096
0x0040a0a0:	movl %edx, %esi
0x0040a0a2:	movl %ecx, %edi
0x0040a0a4:	call 0x0040a105
0x0040a105:	pushl %ebp
0x0040a106:	movl %ebp, %esp
0x0040a108:	subl %esp, $0x14<UINT8>
0x0040a10b:	movb %al, (%edx)
0x0040a10d:	pushl %esi
0x0040a10e:	xorl %esi, %esi
0x0040a110:	incl %esi
0x0040a111:	cmpl 0x8(%ebp), %esi
0x0040a114:	movl -16(%ebp), %ecx
0x0040a117:	movb (%ecx), %al
0x0040a119:	movl -8(%ebp), %esi
0x0040a11c:	movb -1(%ebp), $0x0<UINT8>
0x0040a120:	jbe 483
0x0040a126:	pushl %ebx
0x0040a127:	pushl %edi
0x0040a128:	cmpb -1(%ebp), $0x0<UINT8>
0x0040a12c:	movb %cl, (%edx,%esi)
0x0040a12f:	je 0x0040a13d
0x0040a13d:	incl %esi
0x0040a13e:	andl -12(%ebp), $0x0<UINT8>
0x0040a142:	movb -2(%ebp), %cl
0x0040a145:	movzbl %eax, -1(%ebp)
0x0040a149:	movl %edi, 0x8(%ebp)
0x0040a14c:	subl %edi, %eax
0x0040a14e:	cmpl %esi, %edi
0x0040a150:	jae 416
0x0040a156:	testb %cl, %cl
0x0040a158:	jns 0x0040a275
0x0040a15e:	cmpb -1(%ebp), $0x0<UINT8>
0x0040a275:	cmpb -1(%ebp), $0x0<UINT8>
0x0040a279:	movzbl %ebx, (%edx,%esi)
0x0040a27d:	je 0x0040a28c
0x0040a28c:	movl %edi, -8(%ebp)
0x0040a28f:	movl %eax, -16(%ebp)
0x0040a292:	incl -8(%ebp)
0x0040a295:	movb (%eax,%edi), %bl
0x0040a298:	incl %esi
0x0040a299:	incl -12(%ebp)
0x0040a29c:	shlb %cl
0x0040a29e:	cmpl -12(%ebp), $0x8<UINT8>
0x0040a2a2:	movb -2(%ebp), %cl
0x0040a2a5:	jl 0x0040a145
0x0040a2ab:	jmp 0x0040a2f6
0x0040a2f6:	movzbl %eax, -1(%ebp)
0x0040a2fa:	movl %ecx, 0x8(%ebp)
0x0040a2fd:	subl %ecx, %eax
0x0040a2ff:	cmpl %esi, %ecx
0x0040a301:	jb 0x0040a128
0x0040a162:	movl %ebx, (%edx,%esi)
0x0040a165:	je 0x0040a16a
0x0040a16a:	andl %ebx, $0xfffff<UINT32>
0x0040a170:	incl %esi
0x0040a171:	cmpl -8(%ebp), $0x881<UINT32>
0x0040a178:	movl %edi, %ebx
0x0040a17a:	jae 32
0x0040a17c:	shrl %edi
0x0040a17e:	testb %bl, $0x1<UINT8>
0x0040a181:	je 0x0040a197
0x0040a197:	andl %edi, $0x7f<UINT8>
0x0040a19a:	jmp 0x0040a1e1
0x0040a1e1:	incl %edi
0x0040a1e2:	cmpb -1(%ebp), $0x0<UINT8>
0x0040a1e6:	je 0x0040a1f1
0x0040a1f1:	xorl %ebx, %ebx
0x0040a1f3:	movw %bx, (%edx,%esi)
0x0040a1f7:	andl %ebx, $0xfff<UINT32>
0x0040a1fd:	movzbl %eax, -1(%ebp)
0x0040a201:	xorb -1(%ebp), $0x1<UINT8>
0x0040a205:	addl %esi, %eax
0x0040a207:	movl %eax, %ebx
0x0040a209:	andl %eax, $0xf<UINT8>
0x0040a20c:	cmpl %eax, $0xf<UINT8>
0x0040a20f:	je 5
0x0040a211:	leal %ebx, 0x3(%eax)
0x0040a214:	jmp 0x0040a24e
0x0040a24e:	movl %eax, -8(%ebp)
0x0040a251:	subl %eax, %edi
0x0040a253:	testl %ebx, %ebx
0x0040a255:	je 66
0x0040a257:	movl %edi, -16(%ebp)
0x0040a25a:	addl %eax, %edi
0x0040a25c:	movl -20(%ebp), %ebx
0x0040a25f:	movl %ebx, -8(%ebp)
0x0040a262:	movb %cl, (%eax)
0x0040a264:	incl -8(%ebp)
0x0040a267:	incl %eax
0x0040a268:	decl -20(%ebp)
0x0040a26b:	movb (%edi,%ebx), %cl
0x0040a26e:	jne 0x0040a25f
0x0040a270:	movb %cl, -2(%ebp)
0x0040a273:	jmp 0x0040a299
0x0040a131:	movb %al, 0x1(%edx,%esi)
0x0040a135:	shrb %cl, $0x4<UINT8>
0x0040a138:	shlb %al, $0x4<UINT8>
0x0040a13b:	orb %cl, %al
0x0040a183:	andl %edi, $0x7ff<UINT32>
0x0040a189:	addl %esi, %eax
0x0040a18b:	addl %edi, $0x81<UINT32>
0x0040a191:	xorb -1(%ebp), $0x1<UINT8>
0x0040a195:	jmp 0x0040a1e2
0x0040a1e8:	movzwl %ebx, (%edx,%esi)
0x0040a1ec:	shrl %ebx, $0x4<UINT8>
0x0040a1ef:	jmp 0x0040a1fd
